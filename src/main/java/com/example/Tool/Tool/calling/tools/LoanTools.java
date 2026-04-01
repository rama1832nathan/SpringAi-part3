package com.example.Tool.Tool.calling.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class LoanTools {

    @Tool(description = "Calculate EMI based on principal amount, annual interest rate, and tenure in months")
    public String calculateEmi(double principal, double rate, int months) {
        System.out.println("tool calling called");
        double monthlyRate = rate / (12 * 100);

        double emi = (principal * monthlyRate * Math.pow(1 + monthlyRate, months)) /
                (Math.pow(1 + monthlyRate, months) - 1);

        return "Your EMI is ₹" + String.format("%.2f", emi);
    }
}