import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class polygon {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Point> polygon1 = readPolygon(scanner, "Polygon 1");
        List<Point> polygon2 = readPolygon(scanner, "Polygon 2");

        String shape1 = determineShape(polygon1);
        String shape2 = determineShape(polygon2);

        System.out.println("Polygon 1 is a " + shape1);
        System.out.println("Polygon 2 is a " + shape2);

        if (shape1.equals("triangle") && shape2.equals("triangle")) {
            if (doPolygonsIntersect(polygon1, polygon2)) {
                System.out.println("Triangles intersect");
            } else {
                System.out.println("Triangles do not intersect");
            }
        } else if (shape1.equals("concave") || shape2.equals("concave")) {
            System.out.println("Relationship cannot be determined for concave polygons");
        } else if (shape1.equals("other") || shape2.equals("other")) {
            System.out.println("Relationship cannot be determined for polygons with more than 4 sides");
        } else {
            if (doPolygonsIntersect(polygon1, polygon2)) {
                System.out.println("Polygons intersect");
            } else {
                System.out.println("Polygons are away from each other");
            }
            if (onePolygonInsideOther(polygon1, polygon2)) {
                System.out.println("One polygon lies inside the other");
            }
        }
        

        scanner.close();
    }

    public static List<Point> readPolygon(Scanner scanner, String name) {
        List<Point> polygon = new ArrayList<>();
        System.out.println("Enter the number of vertices for " + name + ":");
        int numVertices = scanner.nextInt();

        for (int i = 0; i < numVertices; i++) {
            System.out.println("Enter x and y coordinates for vertex " + (i + 1) + ":");
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            polygon.add(new Point(x, y));
        }

        return polygon;
    }

    public static String determineShape(List<Point> polygon) {
        int numVertices = polygon.size();
    
        if (numVertices == 3) {
            return "triangle";
        } else if (numVertices == 4) {
            int numRightAngles = countRightAngles(polygon);
            if (numRightAngles == 4) {
                return "square";
            } else if (numRightAngles == 2) {
                return "rectangle";
            }
        }
    
        return "other";
    }
    
    private static int countRightAngles(List<Point> polygon) {
        int count = 0;
    
        for (int i = 0; i < polygon.size(); i++) {
            Point p1 = polygon.get(i);
            Point p2 = polygon.get((i + 1) % polygon.size());
            Point p3 = polygon.get((i + 2) % polygon.size());
    
            if (isRightAngle(p1, p2, p3)) {
                count++;
            }
        }
    
        return count;
    }
    
    private static boolean isRightAngle(Point p1, Point p2, Point p3) {
        int a = (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
        int b = (p1.x - p3.x) * (p1.x - p3.x) + (p1.y - p3.y) * (p1.y - p3.y);
        int c = (p3.x - p2.x) * (p3.x - p2.x) + (p3.y - p2.y) * (p3.y - p2.y);
    
        return a + b == c || a + c == b || b + c == a;
    }
    

    public static boolean doPolygonsIntersect(List<Point> polygon1, List<Point> polygon2) {
        // Check for intersection using the Separating Axis Theorem
        return !isSeparatingAxis(polygon1, polygon2) && !isSeparatingAxis(polygon2, polygon1);
    }

    private static boolean isSeparatingAxis(List<Point> polygon1, List<Point> polygon2) {
        int numVertices1 = polygon1.size();

        for (int i = 0; i < numVertices1; i++) {
            int nextIndex = (i + 1) % numVertices1;
            Point p1 = polygon1.get(i);
            Point p2 = polygon1.get(nextIndex);

            Point axis = new Point(p2.y - p1.y, -(p2.x - p1.x));

            if (axisMagnitude(axis) == 0) {
                continue;
            }

            double min1 = Double.POSITIVE_INFINITY;
            double max1 = Double.NEGATIVE_INFINITY;

            double min2 = Double.POSITIVE_INFINITY;
            double max2 = Double.NEGATIVE_INFINITY;

            for (Point p : polygon1) {
                double dot = dotProduct(axis, p);
                min1 = Math.min(min1, dot);
                max1 = Math.max(max1, dot);
            }

            for (Point p : polygon2) {
                double dot = dotProduct(axis, p);
                min2 = Math.min(min2, dot);
                max2 = Math.max(max2, dot);
            }

            if (max1 < min2 || max2 < min1) {
                return true; // Axis is separating
            }
        }

        return false; // No separating axis found
    }

    private static double axisMagnitude(Point axis) {
        return Math.sqrt(axis.x * axis.x + axis.y * axis.y);
    }

    private static double dotProduct(Point a, Point b) {
        return a.x * b.x + a.y * b.y;
    }

    public static boolean onePolygonInsideOther(List<Point> polygon1, List<Point> polygon2) {
        return isPolygonInside(polygon1, polygon2) || isPolygonInside(polygon2, polygon1);
    }

    private static boolean isPolygonInside(List<Point> innerPolygon, List<Point> outerPolygon) {
        for (Point p : innerPolygon) {
            if (!isPointInsidePolygon(p, outerPolygon)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isPointInsidePolygon(Point point, List<Point> polygon) {
        int crossings = 0;
        int n = polygon.size();

        for (int i = 0; i < n; i++) {
            Point p1 = polygon.get(i);
            Point p2 = polygon.get((i + 1) % n);

            if (point.y > Math.min(p1.y, p2.y) &&
                point.y <= Math.max(p1.y, p2.y) &&
                point.x <= Math.max(p1.x, p2.x) &&
                p1.y != p2.y) {
                
                double xIntersection = (point.y - p1.y) * (p2.x - p1.x) / (p2.y - p1.y) + p1.x;

                if (p1.x == p2.x || point.x <= xIntersection) {
                    crossings++;
                }
            }
        }

        return crossings % 2 != 0;
    }
}

