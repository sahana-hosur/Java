import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

import java.util.LinkedList;
public class sortthread implements ActionListener {
    private static JFrame f;
    private static JLabel label1, label2, rLabel;
    private static JComboBox<String> sortBox, threadbox;
    private static JButton submit;
    private static JPanel result;
    private static DefaultTableModel rTable;
    private static JTable Table;
    private linkedlist list[], list1[], list2[], list3[], list4[];
    LinkedList<Integer> list0[], list01[], list02[], list03[], list04[],temp01,temp02;
    //int[] size = { 0,100000,150000,210000,250000,300000,350000,420000,450000,500000,550000,630000,650000,700000,750000,840000,850000,900000,950000,1000000 };
    int[] size={100000,120000,140000,160000,180000,200000,220000,240000,260000,280000,300000,320000,340000,360000,380000,400000,420000,440000,460000,480000,500000 };
    Object[][] data = new Object[21][2];

    public static void main(String[] args) {
        f = new JFrame("Thread-Sort");
        f.setSize(400, 650);
        f.setLayout(null);
        f.setBackground(Color.BLACK);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label1 = new JLabel("Number of Threads:");
        label1.setFont(new Font("Forte", Font.PLAIN,17));
        label1.setBounds(30, 30, 150, 30);
        f.add(label1);

        label2 = new JLabel("Sorting Method:");
        label2.setFont(new Font("Forte", Font.PLAIN, 17));
        label2.setBounds(45, 70, 150, 30);
        f.add(label2);

        String[] str1 = { "1", "2", "4" };
        threadbox = new JComboBox<>(str1);
        threadbox.setFont(new Font("Forte", Font.PLAIN, 14));
        threadbox.setBackground(Color.WHITE);
        threadbox.setBounds(180, 30, 80, 30);
        f.add(threadbox);

        String[] str2 = { "Bubble Sort", "Merge Sort", "In-Built" };
        sortBox = new JComboBox<>(str2);
        sortBox.setFont(new Font("Forte", Font.PLAIN, 14));
        sortBox.setBackground(Color.WHITE);
        sortBox.setBounds(180, 70, 150, 30);
        f.add(sortBox);

        submit = new JButton("Get Results");
        submit.setFont(new Font("Forte", Font.PLAIN, 12));
        submit.setBounds(150, 110, 95, 30);
        submit.setBackground(Color.gray);
        submit.addActionListener(new sortthread());
        f.add(submit);

        result = new JPanel(null);
        result.setBounds(21, 150, 350, 500);

        rLabel = new JLabel();
        rLabel.setText("");
        rLabel.setFont(new Font("Arial Black", Font.BOLD, 12));
        rLabel.setBounds(140, 10, 90, 21);
        result.add(rLabel);

        rTable = new DefaultTableModel();
        rTable.addColumn("INPUT SIZE");
        rTable.addColumn("Time Taken");

        Table = new JTable(rTable);
        Table.setCellSelectionEnabled(true);
        // ExcelAdapter myAd = new ExcelAdapter(Table);
        result.add(Table);
        JScrollPane pane = new JScrollPane(Table);
        pane.setBounds(10, 40, 280, 370);
        result.add(pane);

        f.getContentPane().add(result);
        f.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (threadbox.getSelectedIndex() == 0) {
            if (sortBox.getSelectedIndex() == 0) {
                list = new linkedlist[21];
                double[] time = new double[21];
                threadbubble t[] = new threadbubble[21];
                for (int i = 0; i < 21; i++) {
                    list[i] = new linkedlist();
                    t[i] = new threadbubble(list[i], size[i]);
                    try {
                        t[i].join();
                    } catch (InterruptedException inte) {
                        JOptionPane.showMessageDialog(null, inte.getMessage());
                    }
                    time[i] = t[i].getTime();
                }
                for (int i = 0; i < size.length; i++) {
                    data[i][0] = size[i];
                    data[i][1] = time[i];
                }
                rTable.setRowCount(0);
                for (Object[] d : data) {
                    rTable.addRow(d);
                }
                Table = new JTable(rTable);
                result.add(Table);
                f.getContentPane().add(result);
                f.repaint();
            } else if (sortBox.getSelectedIndex() == 1) {
                list = new linkedlist[21];
                double[] time = new double[21];
                threadmerge t[] = new threadmerge[21];
                for (int i = 0; i < 21; i++) {
                    list[i] = new linkedlist();
                    t[i] = new threadmerge(list[i], size[i]);
                    try {
                        t[i].join();
                    } catch (InterruptedException inte) {
                        JOptionPane.showMessageDialog(null, inte.getMessage());
                    }
                    time[i] = t[i].getTime();
                }
                for (int i = 0; i < size.length; i++) {
                    data[i][0] = size[i];
                    data[i][1] = time[i];
                }
                rTable.setRowCount(0);
                for (Object[] d : data) {
                    rTable.addRow(d);
                }
                Table = new JTable(rTable);
                result.add(Table);
                f.getContentPane().add(result);
                f.repaint();
            } else {
                list0 = new LinkedList[21];
                double[] time = new double[21];
                threadInBuilt t[] = new threadInBuilt[21];
                for (int i = 0; i < 21; i++) {
                    list0[i] = new LinkedList<>();
                    t[i] = new threadInBuilt(list0[i], size[i]);
                    try {
                        t[i].join();
                    } catch (InterruptedException inte) {
                        JOptionPane.showMessageDialog(null, inte.getMessage());
                    }
                    time[i] = t[i].getTime();
                }
                for (int i = 0; i < size.length; i++) {
                    data[i][0] = size[i];
                    data[i][1] = time[i];
                }
                rTable.setRowCount(0);
                for (Object[] d : data) {
                    rTable.addRow(d);
                }
                Table = new JTable(rTable);
                result.add(Table);
                f.getContentPane().add(result);
                f.repaint();

            }
        } else if (threadbox.getSelectedIndex() == 1) {
            if (sortBox.getSelectedIndex() == 0) {
                list = new linkedlist[21];
                list1 = new linkedlist[21];
                list2 = new linkedlist[21];
                threadbubble t[] = new threadbubble[42];
                double time[] = new double[21];
                for (int i = 0; i < 21; i++) {
                    list1[i] = new linkedlist();
                    list2[i] = new linkedlist();
                    t[i] = new threadbubble(list1[i], size[i] / 2);
                    t[i + 21] = new threadbubble(list2[i], size[i] / 2);
                    try {
                        t[i].join();
                        t[i + 21].join();
                    } catch (InterruptedException inte) {
                        JOptionPane.showMessageDialog(null, inte.getMessage());
                    }
                    time[i] = t[i].getTime() + t[i + 21].getTime();
                }
                for (int i = 0; i < 21; i++) {
                    list[i] = new linkedlist();
                    long start = System.nanoTime();
                    merge(list[i], list1[i], list2[i]);
                    long end = System.nanoTime();
                    double ti = (end - start) * Math.pow(10, -9);
                    time[i] += ti;
                }
                for (int i = 0; i < size.length; i++) {
                    data[i][0] = size[i];
                    data[i][1] = time[i];
                }
                rTable.setRowCount(0);
                for (Object[] d : data) {
                    rTable.addRow(d);
                }
                Table = new JTable(rTable);
                result.add(Table);
                f.getContentPane().add(result);
                f.repaint();
            } else if (sortBox.getSelectedIndex() == 1) {
                list = new linkedlist[21];
                list1 = new linkedlist[21];
                list2 = new linkedlist[21];
                threadmerge t[] = new threadmerge[40];
                double time[] = new double[21];
                for (int i = 0; i < 21; i++) {
                    list1[i] = new linkedlist();
                    list2[i] = new linkedlist();
                    t[i] = new threadmerge(list1[i], size[i] / 2);
                    t[i + 21] = new threadmerge(list2[i], size[i] / 2);
                    try {
                        t[i].join();
                        t[i + 21].join();
                    } catch (InterruptedException inte) {
                        JOptionPane.showMessageDialog(null, inte.getMessage());
                    }
                    time[i] = t[i].getTime() + t[i + 21].getTime();
                }
                for (int i = 0; i < 21; i++) {
                    list[i] = new linkedlist();
                    long start = System.nanoTime();
                    merge(list[i], list1[i], list2[i]);
                    long end = System.nanoTime();
                    double ti = (end - start) * Math.pow(10, -9);
                    time[i] += ti;
                }
                for (int i = 0; i < size.length; i++) {
                    data[i][0] = size[i];
                    data[i][1] = time[i];
                }
                rTable.setRowCount(0);
                for (Object[] d : data) {
                    rTable.addRow(d);
                }
                Table = new JTable(rTable);
                result.add(Table);
                f.getContentPane().add(result);
                f.repaint();
            } else {
                list0 = new LinkedList[21];
                list01 = new LinkedList[21];
                list02 = new LinkedList[21];
                threadInBuilt t[] = new threadInBuilt[42];
                double time[] = new double[21];
                for (int i = 0; i < 21; i++) {
                    list01[i] = new LinkedList();
                    list02[i] = new LinkedList();
                    t[i] = new threadInBuilt(list01[i], size[i] / 2);
                    t[i + 21] = new threadInBuilt(list02[i], size[i] / 2);
                    try {
                        t[i].join();
                        t[i + 21].join();
                    } catch (InterruptedException inte) {
                        JOptionPane.showMessageDialog(null, inte.getMessage());
                    }
                    time[i] = t[i].getTime() + t[i + 21].getTime();
                }
                for (int i = 0; i < 21; i++) {
                    list0[i] = new LinkedList();
                    long start = System.nanoTime();
                    merge(list0[i], list01[i], list02[i]);
                    long end = System.nanoTime();
                    double ti = (end - start) * Math.pow(10, -9);
                    time[i] += ti;
                }
                for (int i = 0; i < size.length; i++) {
                    data[i][0] = size[i];
                    data[i][1] = time[i];
                }
                rTable.setRowCount(0);
                for (Object[] d : data) {
                    rTable.addRow(d);
                }
                Table = new JTable(rTable);
                result.add(Table);
                f.getContentPane().add(result);
                f.repaint();
            }
        } else if(threadbox.getSelectedIndex()==2){
            if (sortBox.getSelectedIndex() == 0) {
                list = new linkedlist[21];
                list1 = new linkedlist[21];
                list2 = new linkedlist[21];
                list3=new linkedlist[21];
                list4=new linkedlist[21];
                threadbubble t[] = new threadbubble[84];
                double time[] = new double[21];
                for (int i = 0; i < 21; i++) {
                    list1[i] = new linkedlist();
                    list2[i] = new linkedlist();
                    list3[i]=new linkedlist();
                    list4[i]=new linkedlist();
                    t[i] = new threadbubble(list1[i], size[i] / 4);
                    t[i+21] = new threadbubble(list2[i], size[i] / 4);
                    t[i+42]=new threadbubble(list3[i], size[i]/4);
                    t[i+63]=new threadbubble(list4[i], size[i]/4);
                    try {
                        t[i].join();
                        t[i+21].join();
                        t[i+42].join();
                        t[i+63].join();
                    } catch (InterruptedException inte) {
                        JOptionPane.showMessageDialog(null, inte.getMessage());
                    }
                    time[i]=t[i].getTime()+t[i+21].getTime()+t[i+42].getTime()+t[i+63].getTime();
                }
                for (int i = 0; i < 21; i++) {
                    linkedlist temp1=new linkedlist();
                    linkedlist temp2=new linkedlist();
                    list[i] = new linkedlist();
                    long start = System.nanoTime();
                    merge(temp1,list1[i], list2[i]);
                    merge(temp2,list3[i],list4[i]);
                    merge(list[i], temp2, temp1);
                    long end = System.nanoTime();
                    double ti = (end - start) * Math.pow(10, -9);
                    time[i] += ti;
                }
                for (int i = 0; i < size.length; i++) {
                    data[i][0] = size[i];
                    data[i][1] = time[i];
                }
                rTable.setRowCount(0);
                for (Object[] d : data) {
                    rTable.addRow(d);
                }
                Table = new JTable(rTable);
                result.add(Table);
                f.getContentPane().add(result);
                f.repaint();
            }else if(sortBox.getSelectedIndex()==1){
                list = new linkedlist[21];
                list1 = new linkedlist[21];
                list2 = new linkedlist[21];
                list3=new linkedlist[21];
                list4=new linkedlist[21];
                threadmerge t[] = new threadmerge[84];
                double time[] = new double[21];
                for (int i = 0; i < 21; i++) {
                    list1[i] = new linkedlist();
                    list2[i] = new linkedlist();
                    list3[i]=new linkedlist();
                    list4[i]=new linkedlist();
                    t[i] = new threadmerge(list1[i], size[i]/4);
                    t[i+21] = new threadmerge(list2[i], size[i]/4);
                    t[i+42]=new threadmerge(list3[i], size[i]/4);
                    t[i+63]=new threadmerge(list4[i], size[i]/4);
                    try {
                        t[i].join();
                        t[i+21].join();
                        t[i+42].join();
                        t[i+63].join();
                    } catch (InterruptedException inte) {
                        JOptionPane.showMessageDialog(null, inte.getMessage());
                    }
                    time[i]=t[i].getTime()+t[i+21].getTime()+t[i+42].getTime()+t[i+63].getTime();
                }
                for (int i = 0; i < 21; i++) {
                    linkedlist temp1=new linkedlist();
                    linkedlist temp2=new linkedlist();
                    list[i] = new linkedlist();
                    long start = System.nanoTime();
                    merge(temp1,list1[i], list2[i]);
                    merge(temp2,list3[i],list4[i]);
                    merge(list[i], temp2, temp1);
                    long end = System.nanoTime();
                    double ti = (end - start) * Math.pow(10, -9);
                    time[i] += ti;
                }
                for (int i = 0; i < size.length; i++) {
                    data[i][0] = size[i];
                    data[i][1] = time[i];
                }
                rTable.setRowCount(0);
                for (Object[] d : data) {
                    rTable.addRow(d);
                }
                Table = new JTable(rTable);
                result.add(Table);
                f.getContentPane().add(result);
                f.repaint();
            }else{
                list0 = new LinkedList[21];
                list01 = new LinkedList[21];
                list02 = new LinkedList[21];
                list03=new LinkedList[21];
                list04=new LinkedList[21];
                threadInBuilt t[] = new threadInBuilt[84];
                double time[] = new double[21];
                for (int i = 0; i < 21; i++) {
                    list01[i] = new LinkedList();
                    list02[i] = new LinkedList();
                    list03[i]=new LinkedList();
                    list04[i]=new LinkedList();
                    t[i] = new threadInBuilt(list01[i], size[i]/4);
                    t[i+21] = new threadInBuilt(list02[i], size[i]/4);
                    t[i+42]=new threadInBuilt(list03[i], size[i]/4);
                    t[i+63]=new threadInBuilt(list04[i], size[i]/4);
                    try {
                        t[i].join();
                        t[i+21].join();
                        t[i+42].join();
                        t[i+63].join();
                    } catch (InterruptedException inte) {
                        JOptionPane.showMessageDialog(null, inte.getMessage());
                    }
                    time[i]=t[i].getTime()+t[i+21].getTime()+t[i+42].getTime()+t[i+63].getTime();
                }
                for (int i = 0; i < 21; i++) {
                    LinkedList temp1=new LinkedList();
                    LinkedList temp2=new LinkedList();
                    list0[i] = new LinkedList();
                    long start = System.nanoTime();
                    merge(temp1,list01[i], list02[i]);
                    merge(temp2,list03[i],list04[i]);
                    merge(list0[i], temp2, temp1);
                    long end = System.nanoTime();
                    double ti = (end - start) * Math.pow(10, -9);
                    time[i] += ti;
                }
                for (int i = 0; i < size.length; i++) {
                    data[i][0] = size[i];
                    data[i][1] = time[i];
                }
                rTable.setRowCount(0);
                for (Object[] d : data) {
                    rTable.addRow(d);
                }
                Table = new JTable(rTable);
                result.add(Table);
                f.getContentPane().add(result);
                f.repaint();
            }
        }
    }
    public static void merge(linkedlist list, linkedlist list1, linkedlist list2) {
        node cur1 = list1.first;
        node cur2 = list2.first;
        while (cur1 != null && cur2 != null) {
            if (cur1.data > cur2.data) {
                list.insert(cur1.data);
                cur1 = cur1.next;
            } else {
                list.insert(cur2.data);
                cur2 = cur2.next;
            }
        }
        while (cur1 != null) {
            list.insert(cur1.data);
            cur1 = cur1.next;
        }
        while (cur2 != null) {
            list.insert(cur2.data);
            cur2 = cur2.next;
        }
    }
    public static void merge(LinkedList<Integer> list, LinkedList<Integer> list1, LinkedList<Integer> list2) {
        LinkedList<Integer> mergedList = new LinkedList<>();
        int index1 = 0;
        int index2 = 0;
        while (index1 < list1.size() && index2 < list2.size()) {
            int element1 = list1.get(index1);
            int element2 = list2.get(index2);
            if (element1 <= element2) {
                mergedList.add(element1);
                index1++;
            } else {
                mergedList.add(element2);
                index2++;
            }
        }
        while (index1 < list1.size()) {
            mergedList.add(list1.get(index1));
            index1++;
        }
        while (index2 < list2.size()) {
            mergedList.add(list2.get(index2));
            index2++;
        }
        list.clear();
        list.addAll(mergedList);
    }
}