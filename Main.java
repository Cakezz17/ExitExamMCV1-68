import Models.*;
import View.*;
import Controller.*;


public class Main {
    public static void main(String[] args) {
        RecruitmentModel model = new RecruitmentModel();
        LoginView loginView = new LoginView();
        RecruitmentController controller = new RecruitmentController(model, loginView);
        loginView.setVisible(true);
    }
}
