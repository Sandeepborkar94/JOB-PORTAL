package com.sdnext.hibernate.tutorial;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class HibernateTestDemo2 {
    public static void main(String[] args) {
        // DB details
        String jdbcURL = "jdbc:mysql://localhost:3306/infosys_practice1";
        String userName = "root";
        String password = "root";

        try {
            // Establishing connection
            Connection connection = DriverManager.getConnection(jdbcURL, userName, password);
            System.out.println("Connected to the database");

            // Creating a statement
            Statement statement = connection.createStatement();

            String sql = "SELECT ord.ORD_FD_SRC_ORD_ID AS ord_fd_src_ord_id, "
                    + "DATE_FORMAT(ord.ORD_TR_TS, '%Y-%m-%dT%H:%i:%s') AS ord_tr_ts, "
                    + "ord.ORD_EF_TDR_ID AS ord_ef_tdr_id, "
                    + "ord.ORD_TR_BKMAP_NM AS ord_tr_ac_id, "
                    + "DATE_FORMAT(ord.ORD_EVT_REC_TS, '%Y-%m-%dT%H:%i:%s') AS ord_evt_rec_ts, "
                    + "ord.OE_RGN_CD AS oe_rgn_cd, "
                    + "ord.ORD_STA_NM AS ord_sta_nm, "
                    + "CASE ord.ORD_STA_NM "
                    + "WHEN 'A' THEN 'NEW' "
                    + "WHEN 'M' THEN 'CFR' "
                    + "WHEN 'D' THEN 'CAN' "
                    + "ELSE ord.ORD_STA_NM END AS ord_evt_ty_nm, "
                    + "ord.ORD_3D_NM AS ord_hdl_ist_cd, "
                    + "ord.ORD_TR_BKMAP_NM AS orex_ist_nm, "
                    + "CASE ord.ORD_STA_NM "
                    + "WHEN '1' THEN 'BUY' "
                    + "WHEN '2' THEN 'SELL' "
                    + "ELSE ord.ORD_STA_NM END AS ord_sd_nm, "
                    + "ord.ORD_INSM_ID AS ord_insm_id, "
                    + "ord.ORD_INI_LCL_AM AS ord_ini_lcl_am, "
                    + "ord.ORD_RMN_QY * ord.ORD_FX_CY_PAIR_RT AS ord_opn_lcl_am, "
                    + "ord.ORD_FX_CY_PAIR_RT AS ord_fx_cy_pair_rt, "
                    + "UPPER(ord.ORD_TR_BKMAP_NM) AS ord_capc_nm, "
                    + "ord.ORD_CLI_PTY_ID AS ord_cli_pty_id, "
                    + "ord.ORD_FD_SRC_NM AS ord_fd_src_nm, "
                    + "DATE_FORMAT(ord.ORD_STL_DT, '%Y-%m-%d') AS ortd_dt "
                    + "FROM single_order ord "
                    + "WHERE ord.COB_DT = '2024-06-18' "
                    + "AND (ord.ORD_FD_SRC_NM = 'FX-SOR' "
                    + "AND ord.ORD_STA_NM IN ('PLACEREQUEST', 'AMENDREQUEST', 'KILLREQUEST') "
                    + "AND UPPER(SUBSTRING_INDEX(SUBSTRING_INDEX(ord.ORD_SUP_CMT_TX, '|', 1), ':', -1)) <> 'TRUE') "
                    + "OR ord.ORD_FD_SRC_NM IN ('MLFX', 'INSTINCT');";

            // Execute the query
            ResultSet resultSet = statement.executeQuery(sql);

            // Process the result set
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No data found.");
            } else {
                while (resultSet.next()) {
                    System.out.println("Order ID: " + resultSet.getString("ord_fd_src_ord_id"));
                    System.out.println("Trade Timestamp: " + resultSet.getString("ord_tr_ts"));
                    System.out.println("Effective Trader ID: " + resultSet.getString("ord_ef_tdr_id"));
                    System.out.println("Region Code: " + resultSet.getString("oe_rgn_cd"));
                    System.out.println("Order Status: " + resultSet.getString("ord_sta_nm"));
                    System.out.println("Event Type: " + resultSet.getString("ord_evt_ty_nm"));
                    System.out.println("Handle List Code: " + resultSet.getString("ord_hdl_ist_cd"));
                    System.out.println("Initial Local Amount: " + resultSet.getString("ord_ini_lcl_am"));
                    System.out.println("Order Open Local Amount: " + resultSet.getString("ord_opn_lcl_am"));
                    System.out.println("--------------------------------------");
                }
            }

            // Closing the connection
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
