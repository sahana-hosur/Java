import java.util.Scanner;
public class calculator {
    private double operand1;
    private double operand2;

        public calculator(double operand1,double operand2){
            this.operand1=operand1;
            this.operand2=operand2;
        }

        public double getOpernad1(){
            return operand1;
        }

        public double getOpernad2(){
            return operand2;
        }
        public double add(){
            return operand1+operand2;
        }

        public double sub(){
            return operand1-operand2;
        } 

        public double mult(){
            return operand1*operand2;
        }

        public double divide()
        {   
            if(operand2==0)
                throw new IllegalArgumentException("Cannot divide by zero");
            return operand1/operand2;
        }

        public static void main(String[] args){
            Scanner scanner=new Scanner(System.in);
            System.out.println("Enter first operand");
            double opernad1=scanner.nextDouble();
            System.out.println("Enter second operand");
            double opernad2=scanner.nextDouble();
            calculator Calculator=new calculator(opernad1,opernad2);
            boolean running =true;
            while(running){
                System.out.println("Enter operation(+,-,*,/)");
                String operation=scanner.next();
                switch(operation){
                    case "+":
                        System.out.println(Calculator.getOpernad1()+"+"+Calculator.getOpernad2()+"="+Calculator.add());
                        break;
                    case "-":
                        System.out.println(Calculator.getOpernad1()+"-"+Calculator.getOpernad2()+"="+Calculator.sub());
                        break;
                    case "*":
                        System.out.println(Calculator.getOpernad1()+"*"+Calculator.getOpernad2()+"="+Calculator.mult());
                        break;
                    case "/":
                        try{
                        System.out.println(Calculator.getOpernad1()+"/"+Calculator.getOpernad2()+"="+Calculator.divide());
                        } catch (IllegalArgumentException e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "q":
                        running=false;
                        break;
                    default:
                        System.out.println("Invalid Operation");
                        break;
                }

            }
            scanner.close();
        }

}
