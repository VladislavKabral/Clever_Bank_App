package com.clevertec.cleverbank.util.printer;

import com.clevertec.cleverbank.dao.BankAccountDAO;
import com.clevertec.cleverbank.dao.BankDAO;
import com.clevertec.cleverbank.model.bank.Bank;
import com.clevertec.cleverbank.model.bank.BankAccount;
import com.clevertec.cleverbank.model.bank.BankTransaction;
import com.clevertec.cleverbank.model.bank.receipt.BankAccountReceipt;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class which represents implementation of Printer interface for printing bank`s transactions
 *
 * @author Vladislav Kabral
 */
@AllArgsConstructor
public class BankTransactionPrinter implements Printer<BankTransaction> {

    /**
     * Field which represents path for saving printed receipt
     */
    private static final String PATH_FOR_SAVE = "./check/";

    /**
     * Field which represents entity for working with banks
     */
    private final BankDAO bankDAO;

    /**
     * Field which represents entity for working with bank`s accounts
     */
    private final BankAccountDAO bankAccountDAO;

    /**
     * Method for printing bank`s transaction to .pdf file
     *
     * @param bankTransaction - entity with data for printing
     */
    @Override
    public void print(BankTransaction bankTransaction) {
        String nameOfFile = "Receipt" + bankTransaction.getDate().getTime() + ".pdf";

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
            List<Paragraph> pdfDocumentContent = generatePDFDocument(bankTransaction, font);

            for (Paragraph item: pdfDocumentContent) {
                document.add(item);
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }

        document.close();
    }

    /**
     * Method for generation .pdf document
     *
     * @param bankTransaction - bank transaction to print
     * @param font - font of document
     * @return list of printing document`s parts
     */
    private List<Paragraph> generatePDFDocument(BankTransaction bankTransaction, Font font) {
        List<Paragraph> pdfDocumentContent = new ArrayList<>();

        Paragraph delimiter = new Paragraph("---------------------------------------", font);
        pdfDocumentContent.add(delimiter);

        Paragraph receiptNumber = new Paragraph("Receipt: " + BankAccountReceipt.RECEIPT_COUNTER++, font);
        pdfDocumentContent.add(receiptNumber);

        Paragraph receiptDate = new Paragraph("Date: " + bankTransaction.getDate(), font);
        pdfDocumentContent.add(receiptDate);

        Paragraph receiptType = new Paragraph("Type: " + bankTransaction.getType(), font);
        pdfDocumentContent.add(receiptType);

        Bank senderBank = bankDAO.findById(bankTransaction.getBankSenderId());
        Paragraph bankSender = new Paragraph("Bank sender: " + senderBank.getName(), font);
        pdfDocumentContent.add(bankSender);

        Bank recipientBank = bankDAO.findById(bankTransaction.getBankRecipientId());
        Paragraph bankRecipient = new Paragraph("Bank recipient: " + recipientBank.getName(), font);
        pdfDocumentContent.add(bankRecipient);

        BankAccount senderBankAccount = bankAccountDAO.findById(bankTransaction.getAccountSenderId());
        Paragraph senderAccount = new Paragraph("Account sender: " + senderBankAccount.getName(), font);
        pdfDocumentContent.add(senderAccount);

        BankAccount recipientBankAccount = bankAccountDAO.findById(bankTransaction.getAccountRecipientId());
        Paragraph recipientAccount = new Paragraph("Account recipient: " + recipientBankAccount.getName(), font);
        pdfDocumentContent.add(recipientAccount);

        Paragraph transactionValue = new Paragraph("Value: " + bankTransaction.getValue(), font);
        pdfDocumentContent.add(transactionValue);

        pdfDocumentContent.add(delimiter);

        return pdfDocumentContent;
    }
}
