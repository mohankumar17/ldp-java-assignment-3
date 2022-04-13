package com.java.assignments.assignment3;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class PingResponseTime {
    public static void printResults(Process process) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = "";
        int count=0;
        ArrayList<Double> timeList=new ArrayList<Double>();
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
            if(count>0){
                int indTime=line.indexOf("time=");
                int indMS=line.indexOf("ms");
                System.out.println(line.substring(indTime+5,indMS-1));
                double time=Double.parseDouble(line.substring(indTime+5,indMS-1));
                timeList.add(time);
            }
            if(count>11){
                break;
            }
            count++;
        }
        Collections.sort(timeList);
        int n=timeList.size();
        System.out.println("Median time taken is: ");
        if(n%2==0){
            double ans=(timeList.get(n/2)+timeList.get((n/2)-1))/2;
            System.out.println(ans);
        }
        else{
            System.out.println(timeList.get(n/2));
        }

    }

    public void sendPingRequest(String ipAddress) throws IOException {
        Process process = Runtime.getRuntime().exec("ping "+ipAddress);
        printResults(process);
    }

    public static void main(String args[]) throws IOException {
        Scanner sc=new Scanner(System.in);
        //System.out.println("Enter the host IP address: ");
        //String ipAddress=sc.nextLine();
        String ipAddress="8.8.4.4";
        PingResponseTime ob=new PingResponseTime();
        ob.sendPingRequest(ipAddress);

    }
}
