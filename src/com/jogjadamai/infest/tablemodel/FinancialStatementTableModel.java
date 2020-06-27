/**
 * Copyright © 2017 Infest Developer Team.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 * * Neither the name of the copyright holder nor the names of its contributors
 *   may be used to endorse or promote products derived from this software 
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package com.jogjadamai.infest.tablemodel;

/**
 * <h1>class <code>FinancialStatementTableModel</code></h1>
 * <p><code>FinancialStatementTableModel</code> is <code>javax.swing.table.AbstractTableModel</code>
 * class defining the table model of <code>Report</code> entity.</p>
 * <br>
 * <p><b><i>Coded, built, and packaged with passion by Adam Afandi for Infest.</i></b></p>
 * 
 * @author Adam Afandi
 * @version 2017.03.10.0001
 */
public final class FinancialStatementTableModel extends javax.swing.table.AbstractTableModel {
    
    private final java.util.List<com.jogjadamai.infest.entity.FinanceReport> financeReportList;
    private final String currency;

    public FinancialStatementTableModel(
            java.util.List<com.jogjadamai.infest.entity.FinanceReport> financeReportList, 
            com.jogjadamai.infest.entity.Features currency) {
        this.financeReportList = financeReportList;
        if(currency.getStatus() == 1) this.currency = " [" + currency.getDescription() + "]";
        else this.currency = "";
    }
    
    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public int getRowCount() {
        return this.financeReportList.size();
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Menu Name";
            case 1:
                return "Menu Price" + this.currency;
            case 2:
                return "Total Order [unit(s)]";
            case 3:
                return "Income" + this.currency;
            case 4:
                return "Status";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int row, int column) {
        java.text.Format dateFormat = new java.text.SimpleDateFormat("dd MMMM yyyy");
        switch (column) {
            case 0:
                return this.financeReportList.get(row).getMenuName();
            case 1:
                return this.financeReportList.get(row).getMenuPrice();
            case 2:
                return this.financeReportList.get(row).getOrderTotal();
            case 3:
                return this.financeReportList.get(row).getIncome();
            case 4:
                switch(this.financeReportList.get(row).getMenuStatus()) {
                    case 0:
                        return "Off the Market (since " + dateFormat.format(this.financeReportList.get(row).getMenuStatusdate()) + ")";
                    case 1:
                        return "On the Market (since " + dateFormat.format(this.financeReportList.get(row).getMenuStatusdate()) + ")";
                    default:
                        break;
                }
            default:
                return null;
        }
    }
}