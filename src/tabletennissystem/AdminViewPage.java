/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabletennissystem;

import javafx.stage.Stage;

/**
 *
 * @author paddy
 */
public class AdminViewPage extends ViewPage {
    
    public AdminViewPage() {
        switchUser.setText("Close");
    }
    
    @Override
    public void switchUser() {
        viewWindow.close();
    }

}
