/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import Model.*;
import View.*;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author Michael Cabusas
 */
public class patientCtlr implements ActionListener{
    private Patients p;
    private PatientsConsult pCon;
    private patientForm pForm;
    private readPanel rPanel;
    private addPanel aPanel;
    private updatePanel uPanel;
    private deletePanel dPanel;
    private updateInfoPanel updatePanel;
    GridBagLayout layout = new GridBagLayout();

    public patientCtlr(Patients p, PatientsConsult pCon, patientForm pForm) throws ClassNotFoundException, SQLException {
        this.p = p;
        this.pCon = pCon;
        this.pForm = pForm;
        this.pForm.getContentPane().setLayout(layout);
        rPanel = new readPanel();
        aPanel = new addPanel();
        uPanel = new updatePanel();
        dPanel = new deletePanel();
        updatePanel = new updateInfoPanel();
        GridBagConstraints c = new GridBagConstraints();
       
        
        
        this.pForm.add.addActionListener(this);
        this.pForm.read.addActionListener(this);
        aPanel.save.addActionListener(new ActionListener() { 
            int flag = 0;
            public void actionPerformed(ActionEvent e) { 
               System.out.print("YES");
                    p.setfName(aPanel.fName.getText());
                    p.setlName(aPanel.lName.getText());
                    p.setGender(aPanel.gender.getSelectedItem().toString());
                    p.setBloodType(aPanel.bloodType.getSelectedItem().toString());
                    p.setAge(Integer.parseInt(aPanel.age.getText()));

                    try {
                        //pCon.insert(p);
                        if(pCon.insert(p)){
                           JOptionPane.showMessageDialog(null, "Success");
                        }else{
                            JOptionPane.showMessageDialog(null, "error");
                        }

                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(patientCtlr.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(patientCtlr.class.getName()).log(Level.SEVERE, null, ex);
              }
            } 
          } );
        
        
        rPanel.update.addActionListener(new ActionListener() { 
            int flag = 0;
            public void actionPerformed(ActionEvent e) { 
                
                aPanel.setVisible(false);
                rPanel.setVisible(false);
                uPanel.setVisible(true);
                dPanel.setVisible(false);
                updatePanel.setVisible(false);
               
            } 
          } );
        
         uPanel.updateBtn.addActionListener(new ActionListener() { 
           
            public void actionPerformed(ActionEvent e) { 
                try {
                    if(pCon.search(Integer.parseInt(uPanel.updateTxtField.getText())) != false){
                        updatePanel.setVisible(true);
                        aPanel.setVisible(false);
                        rPanel.setVisible(false);
                        uPanel.setVisible(false);
                        dPanel.setVisible(false);
                        
                        //updatePanel.fNameUpdate.setText("yes");
                        
                    }else{
                        JOptionPane.showMessageDialog(null, "error");
                    }
                    } catch (ClassNotFoundException ex) {
                    Logger.getLogger(patientCtlr.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(patientCtlr.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
          } );
        
        
        
        //pCon.delete(Integer.parseInt(uPanel.updateTxtField.getText()));
        
        
        rPanel.delete.addActionListener(new ActionListener() { 
            int flag = 0;
            public void actionPerformed(ActionEvent e) { 
                
                aPanel.setVisible(false);
                rPanel.setVisible(false);
                uPanel.setVisible(false);
                dPanel.setVisible(true); 
                updatePanel.setVisible(false);
            } 
          } );
        
        dPanel.deleteBtn.addActionListener(new ActionListener() {   
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        try {
                            if(pCon.delete(Integer.parseInt(dPanel.deleteText.getText()))!=false){
                                JOptionPane.showMessageDialog(null, "Success");
                            }else{
                                JOptionPane.showMessageDialog(null, "Error");
                            }
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(patientCtlr.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(patientCtlr.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                   
               
               });
        updatePanel.saveUpdate.addActionListener(new ActionListener() {
             @Override
                    public void actionPerformed(ActionEvent e) { 
                        p.setfName(updatePanel.fNameUpdate.getText());
                        p.setlName(updatePanel.lNameUpdate.getText());
                        p.setGender(updatePanel.genderUpdate.getSelectedItem().toString());
                        p.setBloodType(updatePanel.bloodTypeUpdate.getSelectedItem().toString());
                        p.setAge(Integer.parseInt(updatePanel.ageUpdate.getText()));
                        p.setId(Integer.parseInt(uPanel.updateTxtField.getText()));

                       try {
                             if(pCon.update(p) == true){
                                    JOptionPane.showMessageDialog(null, "Success");
                             }else{
                                    JOptionPane.showMessageDialog(null, "Error");
                             }
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(patientCtlr.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (SQLException ex) {
                                Logger.getLogger(patientCtlr.class.getName()).log(Level.SEVERE, null, ex);
                            }
                    }             
        });
        
            
        
        
       
        
        
        //for read panel
        
        c.gridx = 0;
        c.gridy = 0;
        this.pForm.add(rPanel,c);
        rPanel.setVisible(false);
        this.pCon.setR(rPanel);
        
        //for add panel
        
        //this.pForm.getContentPane().setLayout(layout);
        c.gridx = 0;
        c.gridy = 0;
        this.pForm.add(aPanel, c);
        aPanel.setVisible(false);
        this.pCon.setA(aPanel);
        
        //for update Panel
        c.gridx = 0;
        c.gridy = 0;
        this.pForm.add(uPanel, c);
        uPanel.setVisible(false);
        this.pCon.setU(uPanel);
        
        //for delete panel
        c.gridx = 0;
        c.gridy = 0;
        this.pForm.add(dPanel, c);
        dPanel.setVisible(false);
        this.pCon.setD(dPanel);
        
        
        //for updatePanel panel
        c.gridx = 0;
        c.gridy = 0;
        this.pForm.add(updatePanel, c);
        updatePanel.setVisible(false);
        this.pCon.setUpdate(updatePanel);
        
    }

    public void start(){
        pForm.setTitle("Blood Bank");
        pForm.setLocationRelativeTo(null);
       // pForm.id.setVisible(false);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == pForm.add){
            aPanel.setVisible(true);
            rPanel.setVisible(false);
            dPanel.setVisible(false);
            uPanel.setVisible(false);
            updatePanel.setVisible(false);
            
            
        }else if(e.getSource() == pForm.read){
            rPanel.setVisible(true);
            aPanel.setVisible(false);
            dPanel.setVisible(false);
            uPanel.setVisible(false);
            updatePanel.setVisible(false);
            try {
                pCon.read();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(patientCtlr.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(patientCtlr.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }

        
        
    }
    
    
    
}
