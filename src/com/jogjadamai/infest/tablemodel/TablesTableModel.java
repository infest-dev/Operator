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
 * <h1>class <code>TablesTableModel</code></h1>
 * <p><code>TablesTableModel</code> is <code>javax.swing.table.AbstractTableModel</code>
 * class defining the table model of <code>Report</code> entity.</p>
 * <br>
 * <p><b><i>Coded, built, and packaged with passion by Adam Afandi for Infest.</i></b></p>
 * 
 * @author Adam Afandi
 * @version 2017.03.10.0001
 */
public final class TablesTableModel extends javax.swing.table.AbstractTableModel {

    private final java.util.List<com.jogjadamai.infest.entity.Tables> TABLES_LIST;

    public TablesTableModel(java.util.List<com.jogjadamai.infest.entity.Tables> tableList) {
        this.TABLES_LIST = tableList;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public int getRowCount() {
        return this.TABLES_LIST.size();
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Table ID";
            case 1:
                return "Name";
            case 2:
                return "Description";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch (column) {
            case 0:
                return this.TABLES_LIST.get(row).getId();
            case 1:
                return this.TABLES_LIST.get(row).getName();
            case 2:
                return this.TABLES_LIST.get(row).getDescription();
            default:
                return null;
        }
    }
}
