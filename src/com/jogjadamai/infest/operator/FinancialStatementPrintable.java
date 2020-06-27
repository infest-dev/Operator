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
package com.jogjadamai.infest.operator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import java.awt.print.PrinterException;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

/**
 * <h1>class <code>FinancialStatementPrintable</code></h1>
 * <p><code>FinancialStatementPrintable</code> printing object class to define
 * what to print when printing Infest Financial Statement.</p>
 * <br>
 * <p><b><i>Coded, built, and packaged with passion by Danang Galuh Tegar P for Infest.</i></b></p>
 * 
 * @author Danang Galuh Tegar P
 * @version 2017.03.10.0001
 */
public final class FinancialStatementPrintable implements Printable {

    private final JTable table;
    private final String date, totalIncome;
    
    public FinancialStatementPrintable(JTable table, String date, String totalIncome) {
        this.table = table;
        this.date = date;
        this.totalIncome = totalIncome;
    }
    
    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.setColor(Color.black); 
        
        int fontHeight = g2d.getFontMetrics().getHeight();
        int fontDescent = g2d.getFontMetrics().getDescent();

        // reserve spaces for page number 
        double pageHeight = pageFormat.getImageableHeight() - fontHeight - 100;
        double pageWidth = pageFormat.getImageableWidth() - 100;
        double tableWidth = (double) table.getColumnModel().getTotalColumnWidth();
        double scale = 1;
        if (tableWidth >= pageWidth) {
            scale = pageWidth / tableWidth;
        }
        double headerHeightOnPage = table.getTableHeader().getHeight() * scale;
        double tableWidthOnPage = tableWidth * scale;
        double oneRowHeight = (table.getRowHeight() + table.getRowMargin()) * scale;
        int numRowsOnAPage = (int)((pageHeight-headerHeightOnPage) / oneRowHeight);
        double pageHeightForTable = oneRowHeight * numRowsOnAPage;
        int totalNumPages = (int)Math.ceil(((double) table.getRowCount()) / numRowsOnAPage);
        if (pageIndex >= totalNumPages)
            return NO_SUCH_PAGE;
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        g2d.drawString("Page: " + (pageIndex +1),(int)pageWidth/2 - 35, (int)(pageHeight + fontHeight - fontDescent - 50));
        g2d.translate(0f, headerHeightOnPage);
        g2d.translate(0f, -pageIndex * pageHeightForTable);
        if (pageIndex + 1 == totalNumPages) {
            int lastRowPrinted = numRowsOnAPage * pageIndex;
            int numRowsLeft = table.getRowCount() - lastRowPrinted;
            g2d.setClip(0, (int)(pageHeightForTable * pageIndex),(int)Math.ceil(tableWidthOnPage),(int)Math.ceil(oneRowHeight * numRowsLeft));
        } else {
            g2d.setClip(0, (int)(pageHeightForTable * pageIndex),(int)Math.ceil(tableWidthOnPage),(int)Math.ceil(pageHeightForTable));
        }
        g2d.scale(scale, scale);
        table.paint(g2d);
        g2d.scale(1/scale, 1/scale);
        g2d.translate(0f, pageIndex*pageHeightForTable);
        g2d.translate(0f, -headerHeightOnPage);
        g2d.setClip(0, 0,(int)Math.ceil(tableWidthOnPage),(int)Math.ceil(headerHeightOnPage));
        g2d.scale(scale, scale);
        table.getTableHeader().paint(g2d);
        
        return Printable.PAGE_EXISTS;
    } 
    
}
