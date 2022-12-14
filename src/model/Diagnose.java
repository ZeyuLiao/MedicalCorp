/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ziyu
 */


public class Diagnose {
    private String diagnose;
    private HashMap<String, String> examins;
    private List<String> treatment; 

    public String getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }

    public List<String> getTreatment() {
        return treatment;
    }

    public void setTreatment(List<String> treatment) {
        this.treatment = treatment;
    }

    public HashMap<String, String> getExamins() {
        return examins;
    }

    public void setExamins(HashMap<String, String> examins) {
        this.examins = examins;
    }

    
    
    public static class Examin {
        private String option;
        private String output;

        public Examin(String option, String output) {
            this.option = option;
            this.output = output;
        }
        

        public String getOption() {
            return option;
        }

        public void setOption(String option) {
            this.option = option;
        }

        public String getOutput() {
            return output;
        }

        public void setOutput(String output) {
            this.output = output;
        }

        @Override
        public String toString() {
            return "Examin{" + "option=" + option + ", output=" + output + '}';
        }
        
        
    }

    @Override
    public String toString() {
        return "Diagnose{" + "diagnose=" + diagnose + ", examins=" + examins + ", treatment=" + treatment + '}';
    }
    
    
}
