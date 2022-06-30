/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imart.GUI;

import com.toedter.calendar.JDateChooser;
import imart.BLL.ThongkeBLL;
import imart.BLL.ctHoaDonBLL;
import imart.BLL.hoaDonBLL;
import imart.BLL.khachHangBLL;
import imart.BLL.loaiHangBLL;
import imart.BLL.nhanVienBLL;
import imart.BLL.sanPhamBLL;
import imart.DAO.connect;
import imart.DTO.Thongke;
import imart.DTO.loaiHang;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Bao Luan
 */
public class thongKePanel {
    private JPanel pnthoigian, pnnhanvien, pnkhachhang, pnsanpham, pnControlTime, pnthoigianleft, pnthoigianright, pnChooseMethod, pnyear;
    private JLabel []lbThongKeType;
    private JLabel lbFromDate, lbToDate, lbChooseMethod;
    private JComboBox cbChooseMethod, cbyear;
    private String []strThongKeType={"Theo quý", "Theo ngày", "Theo năm", "Theo tháng", "Theo loại sản phẩm"};
    private JTabbedPane tabThongKe;
    private String StrChooseMethod[]= {"Báo cáo theo quý","Báo cáo theo ngày","Báo cáo theo tháng","Báo cáo theo năm"};
    hoaDonBLL hdbll= new hoaDonBLL();
    sanPhamBLL spbll=new sanPhamBLL();
    ctHoaDonBLL cthdbll=new  ctHoaDonBLL();
    ThongkeBLL tkbll=new ThongkeBLL();
    loaiHangBLL lhbll=new loaiHangBLL();
    nhanVienBLL nvbll=new nhanVienBLL();
    khachHangBLL khbll=new khachHangBLL();
    connect conn= new connect();
    
    public thongKePanel(){
        initComponents();
        conn.connectSQL();
    }
    
    public void initComponents(){
        UIManager.put("TabbedPane.selected", Color.darkGray);
        tabThongKe = new JTabbedPane();
        tabThongKe.setForeground(Color.white);
        
        Font font= new Font("Serif",Font.BOLD,20);
        pnthoigian= new JPanel();
        //pnthoigian.setBackground(Color.white);
        pnthoigian.setLayout(new BorderLayout(15,0));
        
        pnthoigianleft= new JPanel();
        pnthoigianleft.setLayout(new GridLayout());
        pnthoigianleft.add(getThongKeTheoQuy("2021"));
        
        pnthoigianright= new JPanel();
        pnthoigianright.setPreferredSize(new Dimension(350, 0));
        pnthoigianright.setBackground(Color.white);
        pnthoigianright.setLayout(new FlowLayout(0, 0, 25));
        
        pnthoigian.add(pnthoigianleft,BorderLayout.CENTER);
        pnthoigian.add(pnthoigianright,BorderLayout.EAST);
        
        pnnhanvien= new JPanel();
        pnnhanvien.setBackground(Color.white);
        
        pnkhachhang= new JPanel();
        pnkhachhang.setBackground(Color.white);
        
        pnsanpham= new JPanel();
        pnsanpham.setBackground(Color.white);
        
        tabThongKe.addTab("Thời gian", pnthoigian);
        tabThongKe.addTab("Nhân viên", pnnhanvien);
        tabThongKe.addTab("Khách hàng", pnkhachhang);
        tabThongKe.addTab("Sản phẩm", pnsanpham);
        tabThongKe.setFont(font);
        
        pnControlTime= new JPanel();
        
        cbChooseMethod= new JComboBox(StrChooseMethod);
        ArrayList<String> years_tmp = new ArrayList<>();
        for(int years = 2015; years<=Calendar.getInstance().get(Calendar.YEAR); years++) {
            years_tmp.add(String.valueOf(years));
        }
        cbyear = new JComboBox(years_tmp.toArray());
        cbyear.setSelectedItem(years_tmp.toArray()[years_tmp.toArray().length-1]);
        
        pnyear = new JPanel();
        pnyear.setBackground(Color.white);
        pnyear.setPreferredSize(new Dimension(350, 100));
        pnyear.setLayout(new GridLayout(1,1));
        pnyear.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(null, "Năm", 0, 0, new Font("Tahoma",Font.BOLD,20)),
                                    BorderFactory.createEmptyBorder(15, 20, 15, 20)));
        pnyear.add(cbyear);
        
        pnChooseMethod= new JPanel();
        pnChooseMethod.setBackground(Color.white);
        pnChooseMethod.setPreferredSize(new Dimension(350, 100));
        pnChooseMethod.setLayout(new GridLayout(1,1));
        pnChooseMethod.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(null, "Loại thời gian", 0, 0, new Font("Tahoma",Font.BOLD,20)),
                                    BorderFactory.createEmptyBorder(15, 20, 15, 20)));
        
        pnChooseMethod.add(cbChooseMethod);
        pnthoigianright.add(pnChooseMethod);
        pnthoigianright.add(pnyear);
    }
    public void sp(){
        tkbll=new ThongkeBLL();
        JScrollPane tbtk1 = new javax.swing.JScrollPane();
        JTable tbtk2 = new javax.swing.JTable();
        JPanel c=new JPanel();
        JPanel c1=new JPanel();
        JPanel c2=new JPanel();
        JLabel c11=new JLabel("Từ Ngày: ");
        JDateChooser dateS=new JDateChooser();
        JLabel c21=new JLabel("Đến Ngày: ");
        JDateChooser dateE=new JDateChooser();
        JComboBox<String> Maloai=new JComboBox<>();
        Maloai.addItem("Loai");
        for(loaiHang i:lhbll.list.getLoaiHang()){
            Maloai.addItem(i.getTen_loai());
        }
        dateS.getDateEditor().addPropertyChangeListener(new PropertyChangeListener(){
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                tkbll=new ThongkeBLL();
               SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dates = "";
                String datee = "";
               if(dateE.getDate()==null)
                   datee="99/99/9999";
               else 
                   datee = sdf.format(dateE.getDate());
               if(dateS.getDate()==null)
                   dates="0/0/0";
               else 
                   dates = sdf.format(dateS.getDate());
               if(dateE.getDate()!=null||dateS.getDate()!=null){
                   tkbll.addthongkesp(dates, datee, hdbll, spbll, cthdbll,lhbll);
                   tkdatesp(tbtk2);
               }
            }
            
        });
        dateE.getDateEditor().addPropertyChangeListener(new PropertyChangeListener(){
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                tkbll=new ThongkeBLL();
               SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dates = "";
                String datee = "";
               if(dateS.getDate()==null)
                   dates="0/0/0";
               else 
                    dates = sdf.format(dateS.getDate());
                if(dateE.getDate()==null)
                   datee="99/99/9999";
               else 
                   datee = sdf.format(dateE.getDate());
                if(dateE.getDate()!=null||dateS.getDate()!=null){
                   tkbll.addthongkesp(dates, datee,hdbll,spbll,cthdbll,lhbll);
                   tkdatesp(tbtk2);
                }
               }
            
        });
        
        Maloai.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                tkbll=new ThongkeBLL();
               SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dates = "";
                String datee = "";
               if(dateS.getDate()==null)
                   dates="0/0/0";
               else 
                    dates = sdf.format(dateS.getDate());
                if(dateE.getDate()==null)
                   datee="99/99/9999";
               else 
                   datee = sdf.format(dateE.getDate());
                if(dateE.getDate()!=null||dateS.getDate()!=null){
                   tkbll.addthongkesp(dates, datee,hdbll,spbll,cthdbll,lhbll);
                   tkdatesp(tbtk2);
                }
               }
            
        });
        c1.setPreferredSize(new Dimension(300, 50));
        c1.setMaximumSize(new Dimension(300, 50));
        c1.setMinimumSize(new Dimension(300, 50));
        c1.setBackground(Color.WHITE);
        c2.setBackground(Color.WHITE);
        c.setBackground(Color.WHITE);
        c2.setPreferredSize(new Dimension(430, 50));
        c2.setMaximumSize(new Dimension(430, 200));
        c2.setMinimumSize(new Dimension(430, 200));
        c.setPreferredSize(new Dimension(1230, 500));
        c.setMaximumSize(new Dimension(1230, 500));
        c.setMinimumSize(new Dimension(1230, 500));
        c1.setLayout(new FlowLayout(FlowLayout.CENTER,20,10));
        dateS.setPreferredSize(new Dimension(200,30));
        c2.setLayout(new FlowLayout(FlowLayout.CENTER,20,10));
        c.setLayout(new FlowLayout());
        dateE.setPreferredSize(new Dimension(200,30));
        c1.add(c11);
        c1.add(dateS);
        c2.add(c21);
        c2.add(dateE);
        c.add(c1);
        c.add(c2);
        tbtk2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null,null,null,null},
                {null, null, null, null,null,null,null},
                {null, null, null, null,null,null,null},
                {null, null, null, null,null,null,null}
            },
            new String [] {
                "Masp","Loai", "TenSp", "Soluong", "Gia", "MaNsx","ChucNang"
            }
        ));
         tbtk2.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD,14));
        tbtk2.getTableHeader().setBackground(new Color(100,100,100));
        tbtk2.getTableHeader().setForeground(new Color(255,255,255));
        tbtk2.setBackground(Color.WHITE);
        tbtk1.setPreferredSize(new Dimension(1000,200));
        tbtk1.setMaximumSize(new Dimension(1000,200));
        tbtk1.setMinimumSize(new Dimension(1000,200));
        tbtk2.setPreferredSize(new Dimension(1000,160));
        tbtk2.setGridColor(new Color(243,243,243));
        tbtk2.setRowHeight(60);   
        tbtk2.getTableHeader().setPreferredSize(new Dimension(224,40));
        ((DefaultTableCellRenderer)tbtk2.getTableHeader().getDefaultRenderer())
    .setHorizontalAlignment(JLabel.LEFT);
         tbtk1.setViewportView(tbtk2);
          DefaultTableModel z;
            Object[] ob = new Object[]{"STT","Loai","Masp", "TenSp", "SL Bán", "Doanh Thu"};
            z = new DefaultTableModel(ob, 0);
        
        c.add(tbtk1);
        pnsanpham.setLayout(new FlowLayout());
        pnsanpham.add(c);
        pnsanpham.repaint();
        pnsanpham.revalidate();
   }
   /* Thêm vào */
   public void nv(){
        tkbll=new ThongkeBLL();
        JScrollPane tbnv1 = new javax.swing.JScrollPane();
        JTable tbnv2 = new javax.swing.JTable();
        JPanel d=new JPanel();
        JPanel d1=new JPanel();
        JPanel d2=new JPanel();
        JLabel d11=new JLabel("Từ Ngày: ");
        JDateChooser dateSnv=new JDateChooser();
        JLabel d21=new JLabel("Đến Ngày: ");
        JDateChooser dateEnv=new JDateChooser();
        
        dateSnv.getDateEditor().addPropertyChangeListener(new PropertyChangeListener(){
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                tkbll=new ThongkeBLL();
               SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dates = "";
                String datee = "";
               if(dateEnv.getDate()==null)
                   datee="99/99/9999";
               else 
                   datee = sdf.format(dateEnv.getDate());
               if(dateSnv.getDate()==null)
                   dates="0/0/0";
               else 
                   dates = sdf.format(dateSnv.getDate());
               if(dateEnv.getDate()!=null||dateSnv.getDate()!=null){
                   tkbll.addthongkenv(dates, datee, hdbll, nvbll, cthdbll);
                   tkdatenv(tbnv2);
               }
            }
            
        });
        dateEnv.getDateEditor().addPropertyChangeListener(new PropertyChangeListener(){
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                tkbll=new ThongkeBLL();
               SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dates = "";
                String datee = "";
               if(dateSnv.getDate()==null)
                   dates="0/0/0";
               else 
                    dates = sdf.format(dateSnv.getDate());
                if(dateEnv.getDate()==null)
                   datee="99/99/9999";
               else 
                   datee = sdf.format(dateEnv.getDate());
                if(dateEnv.getDate()!=null||dateSnv.getDate()!=null){
                   tkbll.addthongkenv(dates, datee, hdbll, nvbll, cthdbll);
                   tkdatenv(tbnv2);
                }
               }
            
        });
        d1.setPreferredSize(new Dimension(300, 50));
        d1.setMaximumSize(new Dimension(300, 50));
        d1.setMinimumSize(new Dimension(300, 50));
        d1.setBackground(Color.WHITE);
        d2.setBackground(Color.WHITE);
        d.setBackground(Color.WHITE);
        d2.setPreferredSize(new Dimension(430, 50));
        d2.setMaximumSize(new Dimension(430, 200));
        d2.setMinimumSize(new Dimension(430, 200));
        d.setPreferredSize(new Dimension(1230, 500));
        d.setMaximumSize(new Dimension(1230, 500));
        d.setMinimumSize(new Dimension(1230, 500));
        d1.setLayout(new FlowLayout(FlowLayout.CENTER,20,10));
        dateSnv.setPreferredSize(new Dimension(200,30));
        d2.setLayout(new FlowLayout(FlowLayout.CENTER,20,10));
        d.setLayout(new FlowLayout());
        dateEnv.setPreferredSize(new Dimension(200,30));
        d1.add(d11);
        d1.add(dateSnv);
        d2.add(d21);
        d2.add(dateEnv);
        d.add(d1);
        d.add(d2);
        tbnv2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null,null,null,null},
                {null, null, null, null,null,null,null},
                {null, null, null, null,null,null,null},
                {null, null, null, null,null,null,null}
            },
            new String [] {
                "Manv","Ho", "Ten", "SoluongMua", "Gia", "MaNsx","ChucNang"
            }
        ));
         tbnv2.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD,14));
        tbnv2.getTableHeader().setBackground(new Color(100,100,100));
        tbnv2.getTableHeader().setForeground(new Color(255,255,255));
        tbnv2.setBackground(Color.WHITE);
        tbnv1.setPreferredSize(new Dimension(1000,200));
        tbnv1.setMaximumSize(new Dimension(1000,200));
        tbnv1.setMinimumSize(new Dimension(1000,200));
        tbnv2.setPreferredSize(new Dimension(1000,160));
        tbnv2.setGridColor(new Color(243,243,243));
        tbnv2.setRowHeight(60);   
        tbnv2.getTableHeader().setPreferredSize(new Dimension(224,40));
        ((DefaultTableCellRenderer)tbnv2.getTableHeader().getDefaultRenderer())
    .setHorizontalAlignment(JLabel.LEFT);
         tbnv1.setViewportView(tbnv2);
          DefaultTableModel z;
            Object[] ob = new Object[]{"STT","Manv","Ho", "Ten", "SL Bán", "Doanh Thu"};
            z = new DefaultTableModel(ob, 0);
        tbnv2.setModel(z);
        
        d.add(tbnv1);
        pnnhanvien.setLayout(new FlowLayout());
        pnnhanvien.add(d);
        pnnhanvien.repaint();
        pnnhanvien.revalidate();
   }
   public void kh(){
        tkbll=new ThongkeBLL();
        JScrollPane tbnv1 = new javax.swing.JScrollPane();
        JTable tbnv2 = new javax.swing.JTable();
        JPanel d=new JPanel();
        JPanel d1=new JPanel();
        JPanel d2=new JPanel();
        JLabel d11=new JLabel("Từ Ngày: ");
        JDateChooser dateSnv=new JDateChooser();
        JLabel d21=new JLabel("Đến Ngày: ");
        JDateChooser dateEnv=new JDateChooser();
        
        dateSnv.getDateEditor().addPropertyChangeListener(new PropertyChangeListener(){
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                tkbll=new ThongkeBLL();
               SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dates = "";
                String datee = "";
               if(dateEnv.getDate()==null)
                   datee="99/99/9999";
               else 
                   datee = sdf.format(dateEnv.getDate());
               if(dateSnv.getDate()==null)
                   dates="0/0/0";
               else 
                   dates = sdf.format(dateSnv.getDate());
               if(dateEnv.getDate()!=null||dateSnv.getDate()!=null){
                   tkbll.addthongkekh(dates, datee, hdbll, khbll, cthdbll);
                   tkdatekh(tbnv2);
               }
            }
            
        });
        dateEnv.getDateEditor().addPropertyChangeListener(new PropertyChangeListener(){
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                tkbll=new ThongkeBLL();
               SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dates = "";
                String datee = "";
               if(dateSnv.getDate()==null)
                   dates="0/0/0";
               else 
                    dates = sdf.format(dateSnv.getDate());
                if(dateEnv.getDate()==null)
                   datee="99/99/9999";
               else 
                   datee = sdf.format(dateEnv.getDate());
                if(dateEnv.getDate()!=null||dateSnv.getDate()!=null){
                   tkbll.addthongkekh(dates, datee, hdbll, khbll, cthdbll);
                   tkdatekh(tbnv2);
                }
               }
            
        });
        d1.setPreferredSize(new Dimension(300, 50));
        d1.setMaximumSize(new Dimension(300, 50));
        d1.setMinimumSize(new Dimension(300, 50));
        d1.setBackground(Color.WHITE);
        d2.setBackground(Color.WHITE);
        d.setBackground(Color.WHITE);
        d2.setPreferredSize(new Dimension(430, 50));
        d2.setMaximumSize(new Dimension(430, 200));
        d2.setMinimumSize(new Dimension(430, 200));

        d.setPreferredSize(new Dimension(1230, 500));
        d.setMaximumSize(new Dimension(1230, 500));
        d.setMinimumSize(new Dimension(1230, 500));
        d1.setLayout(new FlowLayout(FlowLayout.CENTER,20,10));
        dateSnv.setPreferredSize(new Dimension(200,30));
        d2.setLayout(new FlowLayout(FlowLayout.CENTER,20,10));
        d.setLayout(new FlowLayout());
        dateEnv.setPreferredSize(new Dimension(200,30));

        d1.add(d11);
        d1.add(dateSnv);
        d2.add(d21);
        d2.add(dateEnv);
        d.add(d1);
        d.add(d2);
        tbnv2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null,null,null,null},
                {null, null, null, null,null,null,null},
                {null, null, null, null,null,null,null},
                {null, null, null, null,null,null,null}
            },
            new String [] {
                "Makh","Ho", "Ten", "SoluongMua", "Gia", "MaNsx","ChucNang"
            }
        ));
         tbnv2.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD,14));
        tbnv2.getTableHeader().setBackground(new Color(100,100,100));
        tbnv2.getTableHeader().setForeground(new Color(255,255,255));
        tbnv2.setBackground(Color.WHITE);
        tbnv1.setPreferredSize(new Dimension(1000,200));
        tbnv1.setMaximumSize(new Dimension(1000,200));
        tbnv1.setMinimumSize(new Dimension(1000,200));
        tbnv2.setPreferredSize(new Dimension(1000,160));
        tbnv2.setGridColor(new Color(243,243,243));
        tbnv2.setRowHeight(60);   
        tbnv2.getTableHeader().setPreferredSize(new Dimension(224,40));
        ((DefaultTableCellRenderer)tbnv2.getTableHeader().getDefaultRenderer())
    .setHorizontalAlignment(JLabel.LEFT);
         tbnv1.setViewportView(tbnv2);
          DefaultTableModel z;
            Object[] ob = new Object[]{"STT","Makh","Ho", "Ten", "SL Bán", "Doanh Thu"};
            z = new DefaultTableModel(ob, 0);
        tbnv2.setModel(z);
        
        d.add(tbnv1);
        pnkhachhang.setLayout(new FlowLayout());
        pnkhachhang.add(d);
        pnkhachhang.repaint();
        pnkhachhang.revalidate();
   }
   /* Thêm vào */
   public void tkdatesp(JTable a){
         DefaultTableModel z=new DefaultTableModel(0, 0);
            Object[] ob = new Object[]{"STT","Loai","Masp", "TenSp", "SL Bán", "Doanh Thu"};
            z = new DefaultTableModel(ob, 0);
            int j=0;
            if(tkbll.listtk.size()==0){
                z = new DefaultTableModel(ob, 0);
                a.setModel(z);
                pnsanpham.repaint();
            pnsanpham.revalidate();
            return;
            }
            else {
            for(Thongke i:tkbll.listtk){
                Object[] item=new Object[6];
                item[0]=j;
                item[1]=i.getID();
                item[2]=i.getChuoi();
                item[3]=i.getTen();
                item[4]=i.getSoluong();
                item[5]=i.getPrice();
                j++;
                z.addRow(item);
            }
            a.setModel(z);
            }
            pnsanpham.repaint();
            pnsanpham.revalidate();
    }
   /* Thêm vào */
   public void tkdatenv(JTable a){
         DefaultTableModel z=new DefaultTableModel(0, 0);
            Object[] ob = new Object[]{"STT","Manv","Ho", "Ten", "SL Bán", "Doanh Thu"};
            z = new DefaultTableModel(ob, 0);
            int j=0;
            if(tkbll.listtk.size()==0){
                 z = new DefaultTableModel(ob, 0);
                a.setModel(z);
                pnnhanvien.repaint();
                pnnhanvien.revalidate();
                return;
            }
            else {
            for(Thongke i:tkbll.listtk){
                Object[] item=new Object[6];
                item[0]=j;
                item[1]=i.getID();
                item[2]=i.getChuoi();
                item[3]=i.getTen();
                item[4]=i.getSoluong();
                item[5]=i.getPrice();
                j++;
                z.addRow(item);
            }
            a.setModel(z);
            }
            pnnhanvien.repaint();
            pnnhanvien.revalidate();
    }
   public void tkdatekh(JTable a){
         DefaultTableModel z=new DefaultTableModel(0, 0);
            Object[] ob = new Object[]{"STT","Makh","Ho", "Ten", "SL Bán", "Doanh Thu"};
            z = new DefaultTableModel(ob, 0);
            int j=0;
            if(tkbll.listtk.size()==0){
                 z = new DefaultTableModel(ob, 0);
                a.setModel(z);
                pnkhachhang.repaint();
                pnkhachhang.revalidate();
                return;
            }
            else {
            for(Thongke i:tkbll.listtk){
                Object[] item=new Object[6];
                item[0]=j;
                item[1]=i.getID();
                item[2]=i.getChuoi();
                item[3]=i.getTen();
                item[4]=i.getSoluong();
                item[5]=i.getPrice();
                j++;
                z.addRow(item);
            }
            a.setModel(z);
            }
            pnkhachhang.repaint();
            pnkhachhang.revalidate();
    }
    public void getThongKePanel(JPanel panel, JPanel pntitle, JLabel lbtitle){
        panel.removeAll();
        panel.setLayout(new BorderLayout(15,15));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 15, 15));

        lbtitle.removeAll();
        lbtitle.setText("Báo cáo doanh thu");
        lbtitle.repaint();
        lbtitle.validate();    
        
        cbChooseMethod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cbChooseMethod.getSelectedIndex()==0){
                    pnthoigianright.add(pnyear);
                    pnthoigianright.repaint();
                    pnthoigianright.validate();
                } else {
                    pnthoigianright.remove(pnyear);
                    pnthoigianright.repaint();
                    pnthoigianright.validate();
                }
            }
        });
        
        cbyear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String year= cbyear.getSelectedItem().toString();
                if(getQuy1(year)!=0.0 || getQuy2(year)!=0.0 || getQuy3(year)!=0.0 || getQuy4(year)!=0.0) {
                    pnthoigianleft.removeAll();
                    pnthoigianleft.add(getThongKeTheoQuy(year));
                    pnthoigianleft.repaint();
                    pnthoigianleft.validate();
                } else {
                    Random rd= new Random();
                    float r =rd.nextFloat();
                    float g =rd.nextFloat();
                    float b =rd.nextFloat();
                    JLabel thongbao= new JLabel("Không có số liệu");
                    thongbao.setFont(new Font("Tahoma",Font.BOLD,100));
                    thongbao.setForeground(new Color(b,0,r));
                    thongbao.setHorizontalAlignment(JLabel.CENTER);
                    pnthoigianleft.removeAll();
                    pnthoigianleft.add(thongbao);
                    pnthoigianleft.repaint();
                    pnthoigianleft.validate();
                }
            }
        });
        
        panel.add(pntitle,BorderLayout.NORTH);
        panel.add(tabThongKe,BorderLayout.CENTER);
        panel.repaint();
        panel.validate();
    }
    
    public ChartPanel getThongKeTheoQuy(String year){
        DefaultCategoryDataset dataSet= new DefaultCategoryDataset();
        dataSet.addValue(getQuy1(year), "Doanh thu", "Quý 1");
        dataSet.addValue(getQuy2(year), "Doanh thu", "Quý 2");
        dataSet.addValue(getQuy3(year), "Doanh thu", "Quý 3");
        dataSet.addValue(getQuy4(year), "Doanh thu", "Quý 4");
        JFreeChart barChart = ChartFactory.createBarChart( "Thống kê doanh thu theo quý", "Năm "+year, "Doanh thu",dataSet, PlotOrientation.VERTICAL, false, false, false);
        ChartPanel chartPanel = new ChartPanel(barChart);
        return chartPanel;
    }
    
    public double getQuy1(String year){
        Double tong=0.0;
        for(int i=0;i<hdbll.getList().getHoadon().size();i++){
            if( year(i).equalsIgnoreCase(year)){
                if(date(i).equals("1") || date(i).equals("2") || date(i).equals("3")){
                    tong+= hdbll.getList().getHoadon().get(i).getTongtien();
                }
            }
        }
        return tong;
    }
    
    public double getQuy2(String year){
        Double tong=0.0;
        for(int i=0;i<hdbll.getList().getHoadon().size();i++){
            if( year(i).equalsIgnoreCase(year)){
                if(date(i).equals("4") || date(i).equals("5") || date(i).equals("6")){
                    tong+= hdbll.getList().getHoadon().get(i).getTongtien();
                }
            }
        }
        return tong;
    }
    
    public double getQuy3(String year){
        Double tong=0.0;
        for(int i=0;i<hdbll.getList().getHoadon().size();i++){
            if( year(i).equalsIgnoreCase(year)){
                if(date(i).equals("7") || date(i).equals("8") || date(i).equals("9")){
                    tong+= hdbll.getList().getHoadon().get(i).getTongtien();
                }
            }
        }
        return tong;
    }
    
    public double getQuy4(String year){
        Double tong=0.0;
        for(int i=0;i<hdbll.getList().getHoadon().size();i++){
            if( year(i).equalsIgnoreCase(year)){
                if(date(i).equals("10") || date(i).equals("11") || date(i).equals("12")){
                    tong+= hdbll.getList().getHoadon().get(i).getTongtien();
                }
            }
        }
        return tong;
    }
    
    public String date(int i){
        LocalDate date = LocalDate.parse((hdbll.getList().getHoadon().get(i).getNgaylap()));
        return String.valueOf(date.getMonthValue());
    }
    
    public String year(int i){
        LocalDate date = LocalDate.parse((hdbll.getList().getHoadon().get(i).getNgaylap()));
        return String.valueOf(date.getYear());
    }
    
    
    public boolean CheckKhoangDay(String dateStart,String dateEnd,String date){
        String[] temp1; temp1 = dateStart.split("-");
        String[] temp2; temp2 = dateEnd.split("-");
        int yearS = Integer.parseInt(temp1[2]);
        int monthS = Integer.parseInt(temp1[1]);
        int dayS = Integer.parseInt(temp1[0]);
        int yearE = Integer.parseInt(temp2[2]);
        int monthE = Integer.parseInt(temp2[1]);
        int dayE = Integer.parseInt(temp2[0]);
        String[] bienlai ;
        bienlai= new String[100];
        int nbl=0;
            String[] mang = date.split("/");
            int year = Integer.parseInt(mang[2]);
            int month = Integer.parseInt(mang[1]);
            int day = Integer.parseInt(mang[0]);
            
            if(yearS==yearE && year==yearS){
                if(month>monthS && month<monthE){
                    return true;
                }
                else{
                    if(month==monthS){
                        if(monthS==monthE){
                            if(day>=dayS && day<=dayE){
                                return true;
                            }
                        }
                        else{
                            if(day>=dayS){
                                return true;
                            }
                        }
                    }
                    else if(month==monthE){
                        if(monthS==monthE){
                            if(day>=dayS && day<=dayE){
                               return true;
                            }
                        }
                        else{
                            if(day<=dayE){
                                return true;
                            }
                        }
                    }
                }
            }
            else{
                if(year>yearS && year<yearE){
                    return true;
                }
                else{
                    if(year==yearS || year==yearE){
                        if(year==yearS){
                            if(month>=monthS){
                                if(month>monthS){
                                    return true;
                                }
                                else{
                                    if(day>=dayS){
                                       return true;
                                    }
                                }
                            }
                        }
                        else{
                            if(month<=monthE){
                                if(month<monthE){
                                    return true;
                                }
                                else{
                                    if(day<=dayE){
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return false;
        }
    
    public void kk(){
        JScrollPane tbtk1 = new javax.swing.JScrollPane();
        JTable tbtk2 = new javax.swing.JTable();
        JPanel c=new JPanel();
        JPanel c1=new JPanel();
        JPanel c2=new JPanel();
        JLabel c11=new JLabel("Từ Ngày: ");
        JDateChooser dateS=new JDateChooser();
        JLabel c21=new JLabel("Đến Ngày: ");
        JDateChooser dateE=new JDateChooser();
        
//        dateS.getDateEditor().addPropertyChangeListener(new PropertyChangeListener(){
//            @Override
//            public void propertyChange(PropertyChangeEvent evt) {
//                dateE.setMinSelectableDate(dateS.getDate());
//               SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//                String dates = "";
//                String datee = "";
//               if(dateE.getDate()==null)
//                   datee="99/99/9999";
//               else 
//                   datee = sdf.format(dateE.getDate());
//               if(dateS.getDate()==null)
//                   dates="0/0/0";
//               else 
//                   dates = sdf.format(dateS.getDate());
//               if(dateE.getDate()!=null||dateS.getDate()!=null){
//                   tkbll.addthongkesp(dates, datee,hdbll,spbll,cthdbll);
////                   tkdate(tbtk2);
//               }
//            }
//            
//        });
//        dateE.getDateEditor().addPropertyChangeListener(new PropertyChangeListener(){
//            @Override
//            public void propertyChange(PropertyChangeEvent evt) {
//                dateS.setMaxSelectableDate(dateS.getDate());
//               SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//                String dates = "";
//                String datee = "";
//               if(dateS.getDate()==null)
//                   dates="0/0/0";
//               else 
//                    dates = sdf.format(dateS.getDate());
//                if(dateE.getDate()==null)
//                   datee="99/99/9999";
//               else 
//                   datee = sdf.format(dateE.getDate());
//                if(dateE.getDate()!=null||dateS.getDate()!=null){
//                   tkbll.addthongkesp(dates, datee,hdbll,spbll,cthdbll);
////                   tkdate(tbtk2);
//                }
//               }
//            
//        });
        c1.setPreferredSize(new Dimension(300, 50));
        c1.setMaximumSize(new Dimension(300, 50));
        c1.setMinimumSize(new Dimension(300, 50));
        c1.setBackground(Color.WHITE);
        c2.setBackground(Color.WHITE);
        c2.setPreferredSize(new Dimension(430, 50));
        c2.setMaximumSize(new Dimension(430, 200));
        c2.setMinimumSize(new Dimension(430, 200));
        c1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLUE));
        c2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLUE));
        c.setPreferredSize(new Dimension(1230, 500));
        c.setMaximumSize(new Dimension(1230, 500));
        c.setMinimumSize(new Dimension(1230, 500));
        c1.setLayout(new FlowLayout(FlowLayout.CENTER,20,10));
        dateS.setPreferredSize(new Dimension(200,30));
        c2.setLayout(new FlowLayout(FlowLayout.CENTER,20,10));
        c.setLayout(new FlowLayout());
        dateE.setPreferredSize(new Dimension(200,30));
        dateS.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLUE));
        dateE.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLUE));
        c1.add(c11);
        c1.add(dateS);
        c2.add(c21);
        c2.add(dateE);
        c.add(c1);
        c.add(c2);
//        tbtk2.setModel(new javax.swing.table.DefaultTableModel(
//            new Object [][] {
//                {null, null, null, null,null,null},
//                {null, null, null, null,null,null},
//                {null, null, null, null,null,null},
//                {null, null, null, null,null,null}
//            },
//            new String [] {
//                "Masp", "TenSp", "Soluong", "Gia", "MaNsx","ChucNang"
//            }
//        ));
//         tbtk2.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD,14));
//        tbtk2.getTableHeader().setBackground(new Color(100,100,100));
//        tbtk2.getTableHeader().setForeground(new Color(255,255,255));
//        tbtk2.setBackground(Color.WHITE);
//        tbtk1.setPreferredSize(new Dimension(1000,200));
//        tbtk1.setMaximumSize(new Dimension(1000,200));
//        tbtk1.setMinimumSize(new Dimension(1000,200));
//        tbtk2.setPreferredSize(new Dimension(1000,160));
//        tbtk2.setGridColor(new Color(243,243,243));
//        tbtk2.setRowHeight(60);
//        tbtk2.getTableHeader().setPreferredSize(new Dimension(224,40));
//        ((DefaultTableCellRenderer)tbtk2.getTableHeader().getDefaultRenderer())
//    .setHorizontalAlignment(JLabel.LEFT);
//         tbtk1.setViewportView(tbtk2);
//          DefaultTableModel z;
//            Object[] ob = new Object[]{"STT","Masp", "TenSp", "SL Bán", "Doanh Thu"};
//            z = new DefaultTableModel(ob, 0);
//        tbtk2.setModel(z);
//        c.add(tbtk1);
        pnsanpham.setLayout(new FlowLayout());
        pnsanpham.add(c1);
        pnsanpham.repaint();
        pnsanpham.revalidate();
   }
    
    
    public static void main(String[] args) {
        thongKePanel t= new thongKePanel();
        
    }
     
}
