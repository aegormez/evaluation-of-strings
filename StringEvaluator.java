/************************************************ 
Author: Ahmet Enes Görmez
Purpose: This class provides functionality for
evaluating strings in simple mathematical operations


*************************************************/


import java.util.Stack;
public class StringEvaluator{
    
    public String calculate(String sample) {
        Stack<Character> mystack = new Stack<Character>();
        char chr;
        for (int i = 0; i < sample.length(); i++) {
            chr = sample.charAt(sample.length() - 1 - i);
            if (chr != ' ') {
                //ASCII values of 44 and 46 are included for future use (in case of input being rational number)
            	if((int)chr>=42&&(int)chr<=57) 
                mystack.push(chr);
            	else {
            		return "Invalid String";
            	}
            }
        }
        char operator=' ';
        double operand1=0;
        double operand2=0;
        boolean operand2Complete=false;
        boolean operand1Complete=false;
        while (!mystack.isEmpty()){
            chr=(char)mystack.peek();
                        
            if( Character.isDigit(chr) && operand1Complete==false ) {
                chr=(char)mystack.pop();
                operand1=10*operand1 + Character.getNumericValue(chr);;

            }else if ((!(Character.isDigit(chr)) && operand1Complete==false)|| operator==' '){
                chr=(char)mystack.pop();
                operator=chr;
                operand1Complete=true;
            }else if(Character.isDigit(chr) && operand2Complete==false){
                chr=(char)mystack.pop();
                operand2=operand2*10+Character.getNumericValue(chr);
                if(mystack.isEmpty()) operand2Complete=true;
            }else if(!(Character.isDigit(chr))&& operand2Complete==false){
                operand2Complete=true;
            }
            if(operand1Complete==true&&operand2Complete==true&&operator!=' '){
                try{
                    chr =(char) mystack.peek();
                }catch(Exception EmptyStackException){
                    chr = ' ';
                }
                try {
                operand1=makeCalculation(operand1,operand2,operator,chr,mystack);
                }catch (Exception EmptyStackException) {
                	return "Invalid String";
                }
                operand2Complete=false;
                operand2=0;
                operator=' ';
            }
        }
        if(operand1Complete==true&&operand2Complete==false&&operator!=' ') {
        	return "Invalid String";
        }
        return ModifyTheResult.Modify(String.valueOf(operand1));

    }
    private static double makeCalculation(double operand1,double operand2,char operator,char nextOperator,Stack incomingStack){
        double result=0;
        switch (operator){
            case '+':
            if(nextOperator=='+'||nextOperator=='-') result = operand1+operand2;
            if(nextOperator=='*'||nextOperator=='/'){
                operand2=executeSubCalculation(operand2,incomingStack);
            }
            result=operand1+operand2;
            break;
            case '-':
            if(nextOperator=='+'||nextOperator=='-') result = operand1-operand2;
            if(nextOperator=='*'||nextOperator=='/'){
                operand2=executeSubCalculation(operand2,incomingStack);
            }
            result=operand1-operand2;
            break;
            case '*':
            result = operand1*operand2;
            break;
            case '/':
            result = operand1/operand2;
            break;
        }
        return result;
    }
    private static double executeSubCalculation(double operand1,Stack incomingStack){
        char operator =(char)incomingStack.pop();
        char cr = (char)incomingStack.peek();
        double operand2=0;
        double result=0;
        while(Character.isDigit(cr)){
            cr=(char)incomingStack.pop();
            operand2=operand2*10+Character.getNumericValue(cr);
            try{
                cr =(char) incomingStack.peek();
            }catch(Exception EmptyStackException){
                cr = ' ';
            }
        }
        if(cr=='+'||cr=='-'||cr==' '){
            switch (operator){
                case '*':
                result= operand1*operand2;
                break;
                case '/':
                result= operand1/operand2;
                break;
            }
        }else if(cr=='*'||cr=='/'){
            switch (operator){
                case '*':
                result= executeSubCalculation(operand1*operand2,incomingStack);
                break;
                case '/':
                result= executeSubCalculation(operand1/operand2,incomingStack);
                break;
            }
        }
        return result;
    }

}
