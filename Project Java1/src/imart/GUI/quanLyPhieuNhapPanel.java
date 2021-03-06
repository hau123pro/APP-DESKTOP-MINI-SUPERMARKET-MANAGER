/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imart.GUI;

import imart.BLL.ctHoaDonBLL;
import imart.BLL.ctPhieuNhapBLL;
import imart.BLL.nhanVienBLL;
import imart.BLL.hoaDonBLL;
import imart.BLL.khachHangBLL;
import imart.BLL.nhaCCapBLL;
import imart.BLL.phieuNhapBLL;
import imart.BLL.sanPhamBLL;
import imart.DAO.nhanVienDAO;
import imart.DAO.taiKhoanDAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Bao Luan
 */
public class quanLyPhieuNhapPanel {
    private JPanel pncontent1, pntab11, pntab12, pntab121, pntab1221,pntab12211, pntab122122, pntab1222, pntab1222chitiet,pnidhd,pntheott, pntheongay, pntheonv, pnnhanvien,
                    pnkhachhang;
    private JLabel lbidhd,lbngaytao, lbmanvtao, lbnhanvien, lbkhachhang;
    private JTextField  txidhd, txngaytao, txmanvtao, txnhanvien, txnhacungcap;
    private JTabbedPane tab= new JTabbedPane();
    private JTabbedPane tab122;
    private static JTable tablekh = new JTable();
    private static DefaultTableModel modelkh= new DefaultTableModel();
    private static JScrollPane scroll= new JScrollPane();
    phieuNhapBLL pnbll= new phieuNhapBLL();
    ctPhieuNhapBLL ctpnbll= new ctPhieuNhapBLL();
    nhanVienDAO nvdao= new nhanVienDAO();
    taiKhoanDAO tkdao= new taiKhoanDAO();
    sanPhamBLL spbll= new sanPhamBLL();
    
    public quanLyPhieuNhapPanel(){
        initComponents();
    }
    
    public void initComponents(){
        //tr??i 
        pncontent1= new JPanel();
        pncontent1.setLayout(new BorderLayout(10,0));
        pncontent1.setLayout(new BorderLayout(15, 10));
        
        //Panel tr??i c???a tab content1
        pntab11= new JPanel();
        pntab11.setBackground(Color.white);
        pntab11.setPreferredSize(new Dimension(350,0));
        pntab11.setLayout(new FlowLayout(0, 0, 15));
        pntab11.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(null, "T??m ki???m", 0, 0, new Font("Tahoma",Font.PLAIN,20)),
                          BorderFactory.createEmptyBorder(10,0,10,0)));
        
        //Panel ph???i c???a tab content1
        pntab12= new JPanel();
        pntab12.setLayout(new BorderLayout(0, 15));
        
        //Panel tr??n c???a panel pntab12
        pntab121= new JPanel();
        pntab121.setLayout(new GridLayout());
        pntab121.setPreferredSize(new Dimension(100,350));
        
        //Tab d?????i c???a panel pntab12
        tab122= new JTabbedPane();
        //tab th??? nh???t c???a tab122
        pntab1221= new JPanel();
        pntab1221.setLayout(new BorderLayout(10, 10));
        //tab th??? hai c???a tab122
        pntab1222= new JPanel();
        pntab1222.setLayout(new GridLayout(2,1));
        
        pntab1222chitiet= new JPanel();
        pntab1222chitiet.setLayout(new GridLayout());
        
        JTable tablectls= new JTable();
        tablectls.setRowHeight(20);
        DefaultTableModel modelctls= new DefaultTableModel();
        JScrollPane scrollctls= new JScrollPane();
        String []cthdban= {"STT","M?? s???n ph???m", "T??n s???n ph???m","????n gi??","S??? l?????ng","T???ng ti???n"};
        modelctls.setColumnIdentifiers(cthdban);
        tablectls.setModel(modelctls);
        scrollctls.setViewportView(tablectls);
        pntab1222chitiet.add(scrollctls);
        pntab1221.add(pntab1222chitiet);
        
        tab122.addTab("Chi ti???t phi???u nh???p",pntab1221);
        tab122.setBackground(Color.lightGray);
        tab122.setForeground(Color.white);
        tab122.setFont(new Font("Tahoma", Font.PLAIN,20));
        
        //panel 1 c???a tab th??? nh???t pntab1221
        pntab12211= new JPanel();
        pntab12211.setBackground(Color.white);
        pntab12211.setPreferredSize(new Dimension(350, 0));
        
        //panel 2 c???a pntab12212
        pntab122122= new JPanel();
        pntab122122.setBackground(Color.white);
        pntab122122.setPreferredSize(new Dimension(0,120));
        pntab122122.setLayout(new GridLayout(1,3,100,0));
        pntab122122.setBorder(BorderFactory.createEmptyBorder(60, 200, 60, 200));
        
        pntab12.add(pntab121,BorderLayout.NORTH);
        pntab12.add(tab122,BorderLayout.CENTER);
                
        pncontent1.add(pntab11, BorderLayout.WEST);
        pncontent1.add(pntab12, BorderLayout.CENTER);
        
        pntheott= new JPanel();
        pntheott.setLayout(new GridLayout(3, 1, 0, 15));
        pntheott.setPreferredSize(new Dimension(340,320));
        pntheott.setBackground(Color.white);
        pntheott.setBorder(BorderFactory.createTitledBorder(null, "Theo th??ng tin phi???u nh???p", 0, 0, new Font("Tahoma",Font.PLAIN,15)));
        
        pnkhachhang= new JPanel();
        pnkhachhang.setLayout(new GridLayout(1, 1,20,0));
        pnkhachhang.setBorder(BorderFactory.createEmptyBorder(30,10,30,10));
        pnkhachhang.setBackground(Color.white);
        
        lbkhachhang= new JLabel("Nh?? cung c???p");
        txnhacungcap= new JTextField();
        
        pnkhachhang.add(lbkhachhang);
        pnkhachhang.add(txnhacungcap);
        
        pnnhanvien= new JPanel();
        pnnhanvien.setLayout(new GridLayout(1, 1,20,0));
        pnnhanvien.setBorder(BorderFactory.createEmptyBorder(30,10,30,10));
        pnnhanvien.setBackground(Color.white);
        
        lbnhanvien= new JLabel("Nh??n vi??n t???o");
        txnhanvien= new JTextField();
        
        pnnhanvien.add(lbnhanvien);
        pnnhanvien.add(txnhanvien);
        
        pnidhd= new JPanel();
        pnidhd.setLayout(new GridLayout(1, 1,20,0));
        pnidhd.setBorder(BorderFactory.createEmptyBorder(30,10,30,10));
        pnidhd.setBackground(Color.white);
        
        lbidhd= new JLabel("M?? phi???u nh???p");
        txidhd= new JTextField();
        
        pnidhd.add(lbidhd);
        pnidhd.add(txidhd);
        
        pntheott.add(pnkhachhang);
        pntheott.add(pnnhanvien);
        pntheott.add(pnidhd);
        
        pntheonv= new JPanel();
        pntheonv.setLayout(new GridLayout(1, 2, 0, 15));
        pntheonv.setPreferredSize(new Dimension(340, 100));
        pntheonv.setBackground(Color.white);
        pntheonv.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(null, "Theo ng?????i t???o h??a ????n", 0, 0, new Font("Tahoma",Font.PLAIN,15)),
                            BorderFactory.createEmptyBorder(20,10,20,10)));
        
        lbmanvtao = new JLabel("M?? nh??n vi??n");
        txmanvtao = new JTextField();
        
        pntheonv.add(lbmanvtao);
        pntheonv.add(txmanvtao);
        
        pntheongay= new JPanel();
        pntheongay.setLayout(new GridLayout(1, 2, 0, 15));
        pntheongay.setPreferredSize(new Dimension(340, 100));
        pntheongay.setBackground(Color.white);
        pntheongay.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(null, "Theo th???i gian", 0, 0, new Font("Tahoma",Font.PLAIN,15)),
                            BorderFactory.createEmptyBorder(20,10,20,10)));
        
        lbngaytao = new JLabel("Th???i gian");
        txngaytao = new JTextField();
        
        pntheongay.add(lbngaytao);
        pntheongay.add(txngaytao);
        
        pntab11.add(pntheott);
//        pntab11.add(pntheonv);
//        pntab11.add(pntheongay);
        
        UIManager.put("TabbedPane.selected", Color.darkGray);
        tab = new JTabbedPane();
    }
    
    public void getDSHoaDonPanel(JPanel panel, JPanel pntitle, JLabel lbtitle){
        panel.removeAll();
        panel.setLayout(new BorderLayout(0,15));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 15, 15));
        
        lbtitle.removeAll();
        lbtitle.setText("Danh s??ch phi???u nh???p");
        lbtitle.repaint();
        lbtitle.validate();       
        
        addFullTable();
        
        txnhacungcap.addKeyListener(new KeyAdapter() {
            @Override 
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE){
                    pntab121.removeAll();
                    pntab1221.removeAll();
                    txnhanvien.setText("");
                    txidhd.setText("");
                    JTable tablectls= new JTable();
                    DefaultTableModel modelctls= new DefaultTableModel();
                    JScrollPane scrollctls= new JScrollPane();
                    String []cthdban= {"STT","M?? s???n ph???m", "T??n s???n ph???m","????n gi??","S??? l?????ng","T???ng ti???n"};
                    modelctls.setColumnIdentifiers(cthdban);
                    tablectls.setModel(modelctls);
                    scrollctls.setViewportView(tablectls);
                    pntab1222chitiet.removeAll();
                    pntab1222chitiet.add(scrollctls);
                    pntab1221.add(pntab1222chitiet);
                    pntab1222chitiet.repaint();
                    pntab1222chitiet.validate();
                    addFullTable();
                    pntab1221.repaint();
                    pntab1221.validate();
                    pntab121.repaint();
                    pntab121.validate();
                } else {
                    nhaCCapBLL nccbll= new nhaCCapBLL();
                    String mancc= nccbll.getMaFromName(txnhacungcap.getText().trim());
                    pntab121.removeAll();
                    txnhanvien.setText("");
                    txidhd.setText("");
                    JTable table= new JTable();
                    table.setRowHeight(20);
                    JScrollPane scroll= new JScrollPane();
                    table= pnbll.showData(mancc);
                    scroll.setViewportView(table);
                    pntab121.add(scroll,BorderLayout.CENTER);
                    pntab1221.removeAll();
                    JTable tablectls= new JTable();
                    DefaultTableModel modelctls= new DefaultTableModel();
                    JScrollPane scrollctls= new JScrollPane();
                    String []cthdban= {"STT","M?? s???n ph???m", "T??n s???n ph???m","????n gi??","S??? l?????ng","T???ng ti???n"};
                    modelctls.setColumnIdentifiers(cthdban);
                    tablectls.setModel(modelctls);
                    scrollctls.setViewportView(tablectls);
                    pntab1222chitiet.removeAll();
                    pntab1222chitiet.add(scrollctls);
                    pntab1221.add(pntab1222chitiet);
                    pntab1222chitiet.repaint();
                    pntab1222chitiet.validate();
                    setCTHDBan2(table);
                    pntab121.repaint();
                    pntab121.validate();
                }
            }
        });
        
        txnhanvien.addKeyListener(new KeyAdapter() {
            @Override 
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE){
                    pntab121.removeAll();
                    pntab1221.removeAll();
                    txnhacungcap.setText("");
                    txidhd.setText("");
                    JTable tablectls= new JTable();
                    DefaultTableModel modelctls= new DefaultTableModel();
                    JScrollPane scrollctls= new JScrollPane();
                    String []cthdban= {"STT","M?? s???n ph???m", "T??n s???n ph???m","????n gi??","S??? l?????ng","T???ng ti???n"};
                    modelctls.setColumnIdentifiers(cthdban);
                    tablectls.setModel(modelctls);
                    scrollctls.setViewportView(tablectls);
                    pntab1222chitiet.removeAll();
                    pntab1222chitiet.add(scrollctls);
                    pntab1221.add(pntab1222chitiet);
                    pntab1222chitiet.repaint();
                    pntab1222chitiet.validate();
                    addFullTable();
                    pntab1221.repaint();
                    pntab1221.validate();
                    pntab121.repaint();
                    pntab121.validate();
                } else {
                    nhanVienBLL nvbll= new nhanVienBLL();
                    String manv= nvbll.getMaFromName(txnhanvien.getText().trim());
                    pntab121.removeAll();
                    txnhacungcap.setText("");
                    txidhd.setText("");
                    JTable table= new JTable();
                    table.setRowHeight(20);
                    JScrollPane scroll= new JScrollPane();
                    table= pnbll.showData(manv);
                    scroll.setViewportView(table);
                    pntab121.add(scroll,BorderLayout.CENTER);
                    pntab1221.removeAll();
                    JTable tablectls= new JTable();
                    DefaultTableModel modelctls= new DefaultTableModel();
                    JScrollPane scrollctls= new JScrollPane();
                    String []cthdban= {"STT","M?? s???n ph???m", "T??n s???n ph???m","????n gi??","S??? l?????ng","T???ng ti???n"};
                    modelctls.setColumnIdentifiers(cthdban);
                    tablectls.setModel(modelctls);
                    scrollctls.setViewportView(tablectls);
                    pntab1222chitiet.removeAll();
                    pntab1222chitiet.add(scrollctls);
                    pntab1221.add(pntab1222chitiet);
                    pntab1222chitiet.repaint();
                    pntab1222chitiet.validate();
                    setCTHDBan2(table);
                    pntab121.repaint();
                    pntab121.validate();
                }
            }
        });
        
        txidhd.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE){
                    pntab121.removeAll();
                    pntab1221.removeAll();
                    txnhacungcap.setText("");
                    txnhanvien.setText("");
                    JTable tablectls= new JTable();
                    DefaultTableModel modelctls= new DefaultTableModel();
                    JScrollPane scrollctls= new JScrollPane();
                    String []cthdban= {"STT","M?? s???n ph???m", "T??n s???n ph???m","????n gi??","S??? l?????ng","T???ng ti???n"};
                    modelctls.setColumnIdentifiers(cthdban);
                    tablectls.setModel(modelctls);
                    scrollctls.setViewportView(tablectls);
                    pntab1222chitiet.removeAll();
                    pntab1222chitiet.add(scrollctls);
                    pntab1221.add(pntab1222chitiet);
                    pntab1222chitiet.repaint();
                    pntab1222chitiet.validate();
                    addFullTable();
                    pntab1221.repaint();
                    pntab1221.validate();
                    pntab121.repaint();
                    pntab121.validate();
                } else {
                    String mapn= txidhd.getText().trim();
                    pntab121.removeAll();
                    txnhacungcap.setText("");
                    txnhanvien.setText("");
                    JTable table= new JTable();
                    table.setRowHeight(20);
                    JScrollPane scroll= new JScrollPane();
                    table= pnbll.showData(mapn);
                    scroll.setViewportView(table);
                    pntab121.add(scroll,BorderLayout.CENTER);
                    pntab1221.removeAll();
                    JTable tablectls= new JTable();
                    DefaultTableModel modelctls= new DefaultTableModel();
                    JScrollPane scrollctls= new JScrollPane();
                    String []cthdban= {"STT","M?? s???n ph???m", "T??n s???n ph???m","????n gi??","S??? l?????ng","T???ng ti???n"};
                    modelctls.setColumnIdentifiers(cthdban);
                    tablectls.setModel(modelctls);
                    scrollctls.setViewportView(tablectls);
                    pntab1222chitiet.removeAll();
                    pntab1222chitiet.add(scrollctls);
                    pntab1221.add(pntab1222chitiet);
                    pntab1222chitiet.repaint();
                    pntab1222chitiet.validate();
                    setCTHDBan2(table);
                    pntab121.repaint();
                    pntab121.validate();
                }
            }
        });
        
        tab.addTab("Phi???u nh???p", pncontent1);
        tab.setBackground(Color.lightGray);
        tab.setForeground(Color.white);
        tab.setFont(new Font("Tahoma", Font.PLAIN,25));
        
        panel.add(pntitle, BorderLayout.NORTH);
        panel.add(tab,BorderLayout.CENTER);
        panel.repaint();
        panel.validate();
    }
    
    public void addFullTable(){
        phieuNhapBLL pnbll= new phieuNhapBLL();
        JTable table= new JTable();
        table.setRowHeight(20);
        JScrollPane scroll= new JScrollPane();
        table= pnbll.getTable();
        scroll.setViewportView(table);
        pntab121.add(scroll,BorderLayout.CENTER);
        setCTHDBan(table);
    }
    
    public void setCTHDBan(JTable table){
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i= table.getSelectedRow();
                int stt=0;
                pntab1222chitiet.removeAll();
                pntab1221.remove(pntab1222chitiet);
                JTable tablectls= new JTable(){
                @Override
                    public boolean isCellEditable(int row, int column) {                
                        switch (column) {
                            default:
                                return false;
                         }              
                    }
                };
                tablectls.setRowHeight(20);
                DefaultTableModel modelctls= new DefaultTableModel();
                String []cthdban= {"STT","M?? s???n ph???m", "T??n s???n ph???m","????n gi??","S??? l?????ng","T???ng ti???n"};
                modelctls.setColumnIdentifiers(cthdban);
                JScrollPane scrollctls= new JScrollPane();
                for(int a= 0;a<ctpnbll.getList().getCtphieunhap().size();a++){
                    if(ctpnbll.getList().getCtphieunhap().get(a).getMa_pn().equals(table.getValueAt(i, 0).toString())){
                        int STT= stt+1;
                        String masp= ctpnbll.getList().getCtphieunhap().get(a).getMa_sp();
                        String tensp= spbll.getSPNameByID(ctpnbll.getList().getCtphieunhap().get(a).getMa_sp());
                        int gia=(int) ctpnbll.getList().getCtphieunhap().get(a).getDongia();
                        int soluong= ctpnbll.getList().getCtphieunhap().get(a).getSoluong();
                        int tong= (int) ctpnbll.getList().getCtphieunhap().get(a).getThanhtien();
                        stt++;
                        Object[] rows= {STT,masp,tensp,gia,soluong,tong};
                        modelctls.addRow(rows);
                    }
                }
                tablectls.setModel(modelctls);
                scrollctls.setViewportView(tablectls);
                pntab1222chitiet.add(scrollctls);
                pntab1221.add(pntab1222chitiet);
                pntab1221.repaint();
                pntab1221.validate();
                pntab1222chitiet.repaint();
                pntab1222chitiet.validate();
            }
        });
    }
    
    public int getCurrentRow(String idhd){
        JTable table= new JTable();
        table= pnbll.getTable();
        for(int i=0;i<table.getRowCount();i++){
            if(table.getValueAt(i, 0).toString().trim().equals(idhd)){
                return i;
            }
        }
        return 0;
    }
    
    public void setCTHDBan2(JTable table){
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JTable table2= new JTable();
                table2= pnbll.getTable();
                int u= table.getSelectedRow();
                int i=0;
                for(int r=0;r<table.getRowCount();r++){
                    if(u == r) {
                        i= getCurrentRow(table.getValueAt(r, 0).toString());
                    }
                }
                int stt=0;
                pntab1222chitiet.removeAll();
                pntab1221.remove(pntab1222chitiet);
                JTable tablectls= new JTable(){
                @Override
                    public boolean isCellEditable(int row, int column) {                
                        switch (column) {
                            default:
                                return false;
                         }              
                    }
                };
                tablectls.setRowHeight(20);
                DefaultTableModel modelctls= new DefaultTableModel();
                String []cthdban= {"STT","M?? s???n ph???m", "T??n s???n ph???m","????n gi??","S??? l?????ng","T???ng ti???n"};
                modelctls.setColumnIdentifiers(cthdban);
                JScrollPane scrollctls= new JScrollPane();
                for(int a= 0;a<ctpnbll.getList().getCtphieunhap().size();a++){
                    if(ctpnbll.getList().getCtphieunhap().get(a).getMa_pn().equals(table2.getValueAt(i, 0).toString())){
                        int STT= stt+1;
                        String masp= ctpnbll.getList().getCtphieunhap().get(a).getMa_sp();
                        String tensp= spbll.getSPNameByID(ctpnbll.getList().getCtphieunhap().get(a).getMa_sp());
                        double gia= ctpnbll.getList().getCtphieunhap().get(a).getDongia();
                        int soluong= ctpnbll.getList().getCtphieunhap().get(a).getSoluong();
                        int tong=(int) ctpnbll.getList().getCtphieunhap().get(a).getThanhtien();
                        stt++;
                        Object[] rows= {STT,masp,tensp,gia,soluong,tong};
                        modelctls.addRow(rows);
                    }
                }
                tablectls.setModel(modelctls);
                scrollctls.setViewportView(tablectls);
                pntab1222chitiet.add(scrollctls);
                pntab1221.add(pntab1222chitiet);
                pntab1221.repaint();
                pntab1221.validate();
                pntab1222chitiet.repaint();
                pntab1222chitiet.validate();
            }
        });
    }
    
}
