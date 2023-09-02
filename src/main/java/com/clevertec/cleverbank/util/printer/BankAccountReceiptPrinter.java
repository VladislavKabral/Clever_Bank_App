package com.clevertec.cleverbank.util.printer;

import com.clevertec.cleverbank.dao.BankAccountDAO;
import com.clevertec.cleverbank.dao.BankDAO;
import com.clevertec.cleverbank.model.bank.Bank;
import com.clevertec.cleverbank.model.bank.BankAccount;
import com.clevertec.cleverbank.model.bank.receipt.BankAccountReceipt;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class BankAccountReceiptPrinter implements Printer<BankAccountReceipt> {

    private static final String PATH_FOR_SAVE = "./check/";

    private final BankDAO bankDAO;

    private final BankAccountDAO bankAccountDAO;

    @Override
    public void print(BankAccountReceipt bankAccountReceipt) {
        String nameOfFile = "Receipt" + bankAccountReceipt.getDate().getTime() + ".pdf";

        Document document = new Document();
        try {
            File file = new File(PATH_FOR_SAVE + nameOfFile);
            if (file.createNewFile()) {
                PdfWriter.getInstance(document, new FileOutputStream(file));
            }
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
        document.open();

        Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);

        try {
            List<Paragraph> pdfDocumentContent = generatePDFDocument(bankAccountReceipt, font);

            for (Paragraph item: pdfDocumentContent) {
                document.add(item);
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }

        document.close();
    }

    private List<Paragraph> generatePDFDocument(BankAccountReceipt bankAccountReceipt, Font font) {
        List<Paragraph> pdfDocumentContent = new ArrayList<>();

        Paragraph delimiter = new Paragraph("---------------------------------------", font);
        pdfDocumentContent.add(delimiter);

        Paragraph receiptNumber = new Paragraph("Receipt: " + BankAccountReceipt.RECEIPT_COUNTER, font);
        pdfDocumentContent.add(receiptNumber);

        Paragraph receiptDate = new Paragraph("Date: " + bankAccountReceipt.getDate(), font);
        pdfDocumentContent.add(receiptDate);

        Paragraph receiptType = new Paragraph("Type: " + bankAccountReceipt.getType(), font);
        pdfDocumentContent.add(receiptType);

        Bank bank = bankDAO.findById(bankAccountReceipt.getBank_id());
        Paragraph receiptBank = new Paragraph("Bank: " + bank.getName(), font);
        pdfDocumentContent.add(receiptBank);

        BankAccount bankAccount = bankAccountDAO.findById(bankAccountReceipt.getAccount_id());
        Paragraph receiptAccount = new Paragraph("Account: " + bankAccount.getName(), font);
        pdfDocumentContent.add(receiptAccount);

        Paragraph receiptValue = new Paragraph("Value: " + bankAccountReceipt.getValue(), font);
        pdfDocumentContent.add(receiptValue);

        pdfDocumentContent.add(delimiter);

        return pdfDocumentContent;
    }
}
