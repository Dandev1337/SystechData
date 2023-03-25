import br.com.systechdata.app.dao.DBconn;
import br.com.systechdata.app.dao.LoginDAO;
import br.com.systechdata.app.form.*;

public class Main {
    public static void main(String[] args) {
        DBconn.createDB();
        DBconn.selectDB();
        DBconn.createTableCliente();
        DBconn.createTableTecnico();
        DBconn.createTableOS();
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TecnicoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TecnicoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TecnicoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TecnicoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        new LoginForm();
    }
}