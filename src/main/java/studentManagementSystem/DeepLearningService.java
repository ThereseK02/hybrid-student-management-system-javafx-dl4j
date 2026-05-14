package studentManagementSystem;

public class DeepLearningService {

    private final DL4JTrainer trainer = new DL4JTrainer();

    public String runTraining() {
        return trainer.trainStudentModel();
    }
}