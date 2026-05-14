package studentManagementSystem;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.factory.Nd4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class StudentDataSetLoader {

    public DataSet loadStudentData(String filePath) throws Exception {
        List<double[]> featuresList = new ArrayList<>();
        List<double[]> labelsList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // skip header
                    continue;
                }

                String[] parts = line.split(",");

                // Expected columns:
                // student_id,age,attendance_rate,assignment_score,midterm_score,
                // final_score,study_hours_per_week,previous_gpa,target_performance

                if (parts.length != 9) {
                    continue;
                }

                double age = Double.parseDouble(parts[1].trim());
                double attendanceRate = Double.parseDouble(parts[2].trim());
                double assignmentScore = Double.parseDouble(parts[3].trim());
                double midtermScore = Double.parseDouble(parts[4].trim());
                double finalScore = Double.parseDouble(parts[5].trim());
                double studyHours = Double.parseDouble(parts[6].trim());
                double previousGpa = Double.parseDouble(parts[7].trim());
                int target = Integer.parseInt(parts[8].trim());

                double[] features = new double[] {
                        age,
                        attendanceRate,
                        assignmentScore,
                        midtermScore,
                        finalScore,
                        studyHours,
                        previousGpa
                };

                // one-hot encoding for binary classification
                double[] label;
                if (target == 1) {
                    label = new double[] {0, 1};
                } else {
                    label = new double[] {1, 0};
                }

                featuresList.add(features);
                labelsList.add(label);
            }
        }

        int numRows = featuresList.size();
        int numFeatures = 7;
        int numLabels = 2;

        INDArray featureMatrix = Nd4j.zeros(numRows, numFeatures);
        INDArray labelMatrix = Nd4j.zeros(numRows, numLabels);

        for (int i = 0; i < numRows; i++) {
            featureMatrix.putRow(i, Nd4j.create(featuresList.get(i)));
            labelMatrix.putRow(i, Nd4j.create(labelsList.get(i)));
        }

        return new DataSet(featureMatrix, labelMatrix);
    }
}