package appdevelopment.timepass;
abstract class Shape {
 protected abstract void draw();
 public void display() {
 System.out.println("Displaying shape.");
 }
}
class Circle extends Shape {
 protected void draw() {
 System.out.println("Drawing a circle.");
 }
}