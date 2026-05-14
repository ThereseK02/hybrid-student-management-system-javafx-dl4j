package studentManagementSystem;

import org.deeplearning4j.eval.Evaluation;
import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.SplitTestAndTrain;
import org.nd4j.linalg.dataset.api.preprocessor.NormalizerStandardize;
import org.nd4j.linalg.learning.config.Adam;
import org.nd4j.linalg.lossfunctions.LossFunctions;

public class DL4JTrainer {

    public String trainStudentModel() {
        try {
            String filePath = "src/main/resources/Generated_Student_Datas.csv";

            StudentDataSetLoader loader = new StudentDataSetLoader();
            DataSet allData = loader.loadStudentData(filePath);

            if (allData == null || allData.isEmpty() || allData.numExamples() < 2) {
                return "Dataset could not be loaded correctly. Loaded rows: "
                        + (allData == null ? 0 : allData.numExamples());
            }

            allData.shuffle();

            SplitTestAndTrain split = allData.splitTestAndTrain(0.8);
            DataSet trainData = split.getTrain();
            DataSet testData = split.getTest();

            NormalizerStandardize normalizer = new NormalizerStandardize();
            normalizer.fit(trainData);
            normalizer.transform(trainData);
            normalizer.transform(testData);

            int numInputs = 7;
            int numHidden1 = 16;
            int numHidden2 = 8;
            int numOutputs = 2;
            int seed = 123;
            int epochs = 150;

            MultiLayerConfiguration config = new NeuralNetConfiguration.Builder()
                    .seed(seed)
                    .weightInit(WeightInit.XAVIER)
                    .updater(new Adam(0.01))
                    .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
                    .list()
                    .layer(new DenseLayer.Builder()
                            .nIn(numInputs)
                            .nOut(numHidden1)
                            .activation(Activation.RELU)
                            .build())
                    .layer(new DenseLayer.Builder()
                            .nIn(numHidden1)
                            .nOut(numHidden2)
                            .activation(Activation.RELU)
                            .build())
                    .layer(new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
                            .nIn(numHidden2)
                            .nOut(numOutputs)
                            .activation(Activation.SOFTMAX)
                            .build())
                    .build();

            MultiLayerNetwork model = new MultiLayerNetwork(config);
            model.init();

            for (int i = 0; i < epochs; i++) {
                model.fit(trainData);
            }

            Evaluation eval = new Evaluation(numOutputs);
            eval.eval(testData.getLabels(), model.output(testData.getFeatures()));

            StringBuilder sb = new StringBuilder();
            sb.append("Training completed successfully.\n\n");
            sb.append("Dataset: student_dataset_200.csv\n");
            sb.append("Total Rows: ").append(allData.numExamples()).append("\n");
            sb.append("Training Rows: ").append(trainData.numExamples()).append("\n");
            sb.append("Testing Rows: ").append(testData.numExamples()).append("\n");
            sb.append("Epochs: ").append(epochs).append("\n\n");

            sb.append("Accuracy: ").append(String.format("%.4f", eval.accuracy())).append("\n");
            sb.append("Precision: ").append(String.format("%.4f", eval.precision())).append("\n");
            sb.append("Recall: ").append(String.format("%.4f", eval.recall())).append("\n");
            sb.append("F1 Score: ").append(String.format("%.4f", eval.f1())).append("\n\n");

            sb.append("Label meaning:\n");
            sb.append("Class 0 = weak performance\n");
            sb.append("Class 1 = strong performance\n");

            return sb.toString();

        } catch (Exception e) {
            return "Training failed:\n" + e.getMessage();
        }

        }
    }

