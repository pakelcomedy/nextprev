package nextprev;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;
import javax.swing.JButton;
import javax.swing.JLabel;

    public class button extends JFrame {
        private JButton nextButton;
        private JButton prevButton;
        private int currentPage = 1;
        private final int rowsPerPage = 3; // Assuming 3 rows per page
        private int totalEntries;
        private JLabel totalEntriesLabel;
    
    public button(JButton next, JButton prev) {
        initComponents();
        this.nextButton = next;
        this.prevButton = prev;
        this.totalEntriesLabel = totalEntriesLabel; // Assign the JLabel
        loadAttendanceData();
        setPaginationLabel();
        nextButton.addActionListener(e -> nextActionPerformed(e));
        prevButton.addActionListener(e -> prevActionPerformed(e));
        this.totalEntriesLabel = totalEntriesLabel; // Assign the JLabel
    }
    
private void loadAttendanceData() {
    DefaultTableModel timeInModel = new DefaultTableModel();
    timeInModel.addColumn("ID");
    timeInModel.addColumn("Jam Masuk");
    timeInModel.addColumn("Nama Pegawai");
    timeInModel.addColumn("Status");

    try {
        int offset = (currentPage - 1) * rowsPerPage;
        String dataSql = "SELECT absensi.id_absensi, absensi.time_in, user.Username, absensi.status_absensi " +
                "FROM absensi " +
                "JOIN user ON absensi.id_user = user.id_user LIMIT ?, ?";
        String countSql = "SELECT COUNT(*) AS totalRows FROM absensi"; // SQL to get total row count
        try (Connection conn = koneksi.configDB();
             PreparedStatement dataStmt = conn.prepareStatement(dataSql);
             PreparedStatement countStmt = conn.prepareStatement(countSql)) {

            // Execute query to get total row count
            try (ResultSet countResult = countStmt.executeQuery()) {
                if (countResult.next()) {
                    totalEntries = countResult.getInt("totalRows");
                }
            }

            dataStmt.setInt(1, offset);
            dataStmt.setInt(2, rowsPerPage);
            try (ResultSet res = dataStmt.executeQuery()) {
                while (res.next()) {
                    String idAbsensi = res.getString("id_absensi");
                    String waktuAbsensi = res.getString("time_in");
                    String namaPegawai = res.getString("Username");
                    String statusAbsensi = res.getString("status_absensi");

                    // Extracting time in "HH:mm" format from datetime string
                    String timeIn = waktuAbsensi.substring(11, 16);

                    timeInModel.addRow(new Object[]{idAbsensi, timeIn, namaPegawai, statusAbsensi});
                }
            }
        }
        timein.setModel(timeInModel);
        setTableAlignment(timein);

        // Update total entries label
        updateTotalEntriesLabel(offset, timeInModel.getRowCount());
    } catch (Exception e) {
        handleException("Error loading time in data", e);
    }
}

private void updateTotalEntriesLabel(int startRow, int endRow) {
    // Calculate the actual start row and end row based on the current page and rows per page
    startRow = Math.min(Math.max((currentPage - 1) * rowsPerPage + 1, 1), totalEntries);
    endRow = Math.min(startRow + rowsPerPage - 1, totalEntries);

    total.setText("Showing " + startRow + " to " + endRow + " of " + totalEntries + " entries");
}

    private void setPaginationLabel() {
        num1.setText(String.valueOf(currentPage));
    }
    
    private int getMaxPage() {
    return (int) Math.ceil((double) totalEntries / rowsPerPage);
}

    private void setTableAlignment(JTable table) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    private void handleException(String message, Exception e) {
        System.err.println(message);
        e.printStackTrace();
        // Optionally, display a dialog or log the exception to a file.
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        total = new javax.swing.JLabel();
        num1 = new javax.swing.JLabel();
        prev = new javax.swing.JButton();
        next = new javax.swing.JButton();
        table1 = new javax.swing.JScrollPane();
        timein = new javax.swing.JTable();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(438, 270));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        total.setText("-");
        getContentPane().add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, -1, -1));

        num1.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        num1.setForeground(new java.awt.Color(255, 255, 255));
        num1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        num1.setText("1");
        getContentPane().add(num1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 210, 20, -1));

        prev.setBorder(null);
        prev.setBorderPainted(false);
        prev.setContentAreaFilled(false);
        prev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevActionPerformed(evt);
            }
        });
        getContentPane().add(prev, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 210, 40, 20));

        next.setBorder(null);
        next.setBorderPainted(false);
        next.setContentAreaFilled(false);
        next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextActionPerformed(evt);
            }
        });
        getContentPane().add(next, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 210, 30, 20));

        timein.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        timein.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Jam Masuk", "Nama Pegawai", "Status"
            }
        ));
        timein.setRowHeight(30);
        table1.setViewportView(timein);

        getContentPane().add(table1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 400, 120));

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/nextprev/bg.png"))); // NOI18N
        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextActionPerformed
    if (currentPage < getMaxPage()) { // Check if currentPage is less than the maximum page
        currentPage++;
        loadAttendanceData();
        setPaginationLabel();
    }
    }//GEN-LAST:event_nextActionPerformed

    private void prevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevActionPerformed
    if (currentPage > 1) {
        currentPage--;
        loadAttendanceData();
        setPaginationLabel();
    }
    }//GEN-LAST:event_prevActionPerformed
        public static void main(String args[]) {
        JButton next = new JButton("Next");
        JButton prev = new JButton("Previous");
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new button(next, prev).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bg;
    private javax.swing.JButton next;
    private javax.swing.JLabel num1;
    private javax.swing.JButton prev;
    private javax.swing.JScrollPane table1;
    private javax.swing.JTable timein;
    private javax.swing.JLabel total;
    // End of variables declaration//GEN-END:variables
}