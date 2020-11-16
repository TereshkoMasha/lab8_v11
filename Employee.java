package com.company;

import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    String departmentNumber;
    static final String P_departmentNumber = "Department number";
    String personnelNumber;
    static final String P_personnelNumber = "Personnel number";
    String fullName;
    static final String P_fullName = "Full name";
    double salary;
    static final String P_salary = "Salary";
    int enrollmentDate;
    static final String P_enrollmentDate = "Enrollment year";
    double premium;
    static final String P_premium = "Premium";
    double incomeTax;
    static final String P_incomeTax = "Income tax";

    public static final String fullNameDel = ",";

    private static GregorianCalendar curCalendar = new GregorianCalendar();
    static Boolean validYear( int year ) {
        return year > 1945 && year <= curCalendar.get( Calendar.YEAR );
    }

    static Boolean nextRead( final String prompt, Scanner fin, PrintStream out ) {
        out.print( prompt );
        out.print( ": " );
        return fin.hasNextLine();
    }

    static Boolean validDepartmentNumber( String str ) {
        int i = 0, ndig = 0;
        for ( ; i < str.length(); i++ ) {
            char ch = str.charAt(i);
            if ( Character.isDigit(ch) ) {
                ndig++;
                continue;
            }
            else return false;
        }
        return (ndig == 5 || ndig == 7 );
    }
    public static Boolean nextRead( Scanner fin, PrintStream out ) {
        return nextRead( P_departmentNumber, fin, out );
    }

    public static Employee read( Scanner fin, PrintStream out ) throws IOException {
        String str;
        Employee employee = new Employee();
        employee.departmentNumber = fin.nextLine();
        if ( Employee.validDepartmentNumber( employee.departmentNumber )== false ) {
            throw new IOException("Incorrect input ");
        }
        if ( ! nextRead( P_personnelNumber, fin, out ))           return null;
        employee.personnelNumber = fin.nextLine();
        if(Integer.parseInt(employee.personnelNumber)<0){
            throw new IOException("Invalid personnel number value");
        }
        if ( ! nextRead( P_fullName, fin, out ))             return null;
        employee.fullName = fin.nextLine();
        if ( ! nextRead( P_enrollmentDate, fin, out ))             return null;
        str = fin.nextLine();
        employee.enrollmentDate = Integer.parseInt(str);
        if ( Employee.validYear(employee.enrollmentDate) ==  false ) {
            throw new IOException("Invalid Staff.year value");
        }
        if ( ! nextRead( P_salary, fin, out ))        return null;
        str = fin.nextLine();
        employee.salary = Integer.parseInt(str);
        if ( ! nextRead( P_premium, fin, out ))       return null;
        str = fin.nextLine();
        employee.premium = Double.parseDouble(str);
        if ( ! nextRead( P_incomeTax, fin, out ))            return null;
        str = fin.nextLine();
        employee.incomeTax = Double.parseDouble(str);
        if(employee.salary<0 || employee.premium < 0 || employee.incomeTax<0)
        {
            throw  new IOException("Invalid input: Check salary, premium or income tax input");
        }
        return employee;
    }
    public Employee() {
    }

    public String toString() {
        return new String (
                departmentNumber + "|" +
                        personnelNumber + "|" +
                        fullName + "|" +
                        salary + " $ |" +
                        enrollmentDate + " year |" +
                        premium + " $ |" +
                        incomeTax + " %"
        );
    }
}
