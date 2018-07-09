/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bloodbank;

import Controller.*;
import Model.*;

import View.*;
import java.sql.SQLException;

/**
 *
 * @author Michael Cabusas
 */
public class BloodBank {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Patients p = new Patients();
        PatientsConsult pCon = new PatientsConsult();
        patientForm view = new patientForm();
        
        
        patientCtlr pControl = new patientCtlr(p, pCon, view);
        
        pControl.start();
       
        view.setVisible(true);
       
    }
    
}
