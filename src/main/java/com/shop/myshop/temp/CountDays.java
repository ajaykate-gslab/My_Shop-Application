package com.shop.myshop.temp;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CountDays {
    public void dateDifference() throws Exception {

        Scanner scanner=new Scanner(System.in);
        /*    int[] arr={31,28,31,30,31,30,31,31,30,31,30,31};
        int year=0,month = 0,day=0,sum=0;
        System.out.println("Enter Start date in format (yyyy mm dd):");
        int y1=scanner.nextInt();
        int m1=scanner.nextInt();
        int d1=scanner.nextInt();

        System.out.println("Enter End date in format (yyyy mm dd):");
        int y2=scanner.nextInt();
        int m2=scanner.nextInt();
        int d2=scanner.nextInt();

        if(y1%4==0 || y2%4==0){
             year=(y2-y1)*365+1;

                // month = (m2 - m1) * 30;
                 for(int i=m1-1;i<m2;i++){
                     month=month+arr[i];

                 }
             day=(d2-d1);
            long Totaldays=year+month+day;
            System.out.println("Days.. :"+Totaldays);
        }
        else {
            year=(y2-y1)*365;
            for(int i=0;i<(12);i++){
                //if(arr[ i-1]==m1) {
                    month = month + arr[i];
            }
            day=(d2-d1);
            long Totaldays=year+month+day;
            System.out.println("Days :-"+Totaldays);
        }*/
        //-------------

                int[] arr={31,28,31,30,31,30,31,31,30,31,30,31};
                int year=0,month = 0,day=0,sum=0;
                int count=0;

                //String startDate=scanner.nextLine();
                String startDate="5/4/2025";
                String endDate="11/3/2029";
                String[] arr1=startDate.split("/");
                int d1=Integer.parseInt(arr1[0]);
                int m1=Integer.parseInt(arr1[1]);
                int y1=Integer.parseInt(arr1[2]);

                //String endDate=scanner.nextLine();

                String[] arr2=endDate.split("/");
                int d2=Integer.parseInt(arr2[0]);
                int m2=Integer.parseInt(arr2[1]);
                int y2=Integer.parseInt(arr2[2]);

                LocalDate date= LocalDate.of(y1,m1,d1);
                LocalDate date1=LocalDate.of(y2,m2,d2);

                System.out.println(d1+"/"+m1+"/"+y1);
                System.out.println(d2+"/"+m2+"/"+y2);

                if(y1<y2) {
                    for(int i=y1+1;i<y2;i++) {
                        if(i%4==0) {
                            count++;
                        }

                    }
                    year=((y2-y1) *365)+count;
                }
                else
                {
                    for(int i=y2;i>y1;i--) {
                        if(i%4==0) {
                            count++;
                        }
                    }
                    year=((y1-y2) *365)+count;
                }

                System.out.println("year :"+year);
                //int[] arr={31,28,31,30,31,30,31,31,30,31,30,31};


                for(int i=0;i<arr.length;i++) {

                    if(m1>m2) {
                        if(i<=m1-1 && i>m2-1)
                        {
                            month=month+arr[i];
                        }
                    }else if (m2>m1) {
                        if(i>=m1-1 && i<m2-1)
                        {
                            month=month+arr[i];
                        }
                    }
                }
                System.out.println("month "+month);

                if(d1>=d2) {
                    day =d1-d2;
                }
                else
                {
                    day=d2-d1;
                }
                System.out.println("Day :"+day);
                if(m1>m2)
                {
                    if(d1>d2)
                    {
                        System.out.println("TotalDays :"+(year-month-day));
                    }
                    else//(d2>d1)
                    {
                        System.out.println("TotalDays :"+(year-month+day));
                    }
                }
                else if(m1<m2){
                    if(d1<d2)
                    {
                        System.out.println("TotalDays :"+(year+month+day));
                    }
                    else//(d1>d2)
                    {
                        System.out.println("TotalDays :"+(year+month-day));
                    }
                }
            }
        }