package appdevelopment.timepass;
interface IA {
 void methA();
}
class B implements IA {
 public void methA() {
 System.out.println("inside methA");
 }
}
public class Demo {
 public static void main(String[] args) {
 B b = new B();
 b.methA();
 }
}