package org.example;

import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class Formular extends JFrame {

    private JRadioButton calculatoare;
    private JRadioButton ti;
    private JRadioButton aia;

    Formular() {
        JPanel primaLinie = new JPanel();
        primaLinie.setBounds(0, 0, 750, 50);

        JLabel titlu = new JLabel("Formular Student");
        titlu.setFont(new Font("Dialog", Font.PLAIN, 30));

        JPanel liniaDoi = new JPanel();
        liniaDoi.setBounds(0, 100, 750, 50);
        liniaDoi.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 0));

        JLabel nume = new JLabel("Nume:");
        nume.setFont(new Font("Dialog", Font.PLAIN, 30));

        JTextField numeText = new JTextField();
        numeText.setPreferredSize(new Dimension(300, 40));

        JPanel liniaTrei = new JPanel();
        liniaTrei.setBounds(0, 150, 750, 50);
        liniaTrei.setLayout(new FlowLayout(FlowLayout.CENTER, 45, 0));

        JLabel prenume = new JLabel("Prenume:");
        prenume.setFont(new Font("Dialog", Font.PLAIN, 30));

        JTextField prenumeText = new JTextField();
        prenumeText.setPreferredSize(new Dimension(300, 40));

        JPanel liniaPatru = new JPanel();
        liniaPatru.setBounds(0, 250, 750, 50);

        JLabel specializare = new JLabel("Specializare:");
        specializare.setFont(new Font("Dialog", Font.PLAIN, 30));

        JPanel liniaCinci = new JPanel();
        liniaCinci.setBounds(0, 300, 750, 50);
        liniaCinci.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 0));

        calculatoare = new JRadioButton("Calculatoare");
        ti = new JRadioButton("TI");
        aia = new JRadioButton("AIA");
        calculatoare.setFont(new Font("Dialog", Font.PLAIN, 20));
        ti.setFont(new Font("Dialog", Font.PLAIN, 20));
        aia.setFont(new Font("Dialog", Font.PLAIN, 20));

        ButtonGroup group = new ButtonGroup();
        group.add(calculatoare);
        group.add(ti);
        group.add(aia);

        JLabel an = new JLabel("An:");
        an.setFont(new Font("Dialog", Font.PLAIN, 30));

        JPanel liniaSase = new JPanel();
        liniaSase.setBounds(0, 400, 750, 60);

        String[] anAlegere = {"I", "II", "III", "IV"};
        JComboBox<String> comboBox = new JComboBox<>(anAlegere);
        comboBox.setFont(new Font("Dialog", Font.PLAIN, 20));

        JPanel liniaSapte = new JPanel();
        liniaSapte.setBounds(0, 650, 750, 70);

        JButton save = new JButton("Salveaza");
        save.setFont(new Font("Dialog", Font.PLAIN, 25));
        JButton cancel = new JButton("Inchide");
        cancel.setFont(new Font("Dialog", Font.PLAIN, 25));


        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvareDateJSON(numeText.getText(), prenumeText.getText(), getSpecializare(), (String) comboBox.getSelectedItem());
            }
        });

        primaLinie.add(titlu);
        liniaDoi.add(nume);
        liniaDoi.add(numeText);
        liniaTrei.add(prenume);
        liniaTrei.add(prenumeText);
        liniaPatru.add(specializare);
        liniaCinci.add(calculatoare);
        liniaCinci.add(ti);
        liniaCinci.add(aia);
        liniaSase.add(an);
        liniaSase.add(comboBox);
        liniaSapte.add(save);
        liniaSapte.add(cancel);
        this.add(liniaDoi);
        this.add(liniaTrei);
        this.add(liniaPatru);
        this.add(liniaCinci);
        this.add(liniaSase);
        this.add(liniaSapte);
        this.setTitle("Formular");
        this.add(primaLinie);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(750, 750);

        this.setVisible(true);
    }

    private String getSpecializare() {
        if (calculatoare.isSelected()) {
            return "Calculatoare";
        } else if (ti.isSelected()) {
            return "TI";
        } else if (aia.isSelected()) {
            return "AIA";
        }
        return "";
    }

    private void salvareDateJSON(String nume, String prenuma, String specialzare, String an) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Nume", nume);
        jsonObject.put("Prenume", prenuma);
        jsonObject.put("Specializare", specialzare);
        jsonObject.put("An", an);

        try (FileWriter fileWriter = new FileWriter("date_studenti.json", true)) {
            fileWriter.write(jsonObject.toJSONString() + "\n");
            fileWriter.flush();
            JOptionPane.showMessageDialog(this, "Datele au fost salvate cu succes!");
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "A apărut o eroare la salvarea datelor.", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }
}