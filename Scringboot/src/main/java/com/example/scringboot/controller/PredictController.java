package com.example.scringboot.controller;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//@RestController
//@RequestMapping("/api")
//public class PredictController {
//
//    @GetMapping("/predict")
//    public String predict() throws IOException {
//        // 调用Python脚本的路径和命令
//        String pythonScriptPath = "D:\\个人桌面\\SmartEye\\2.py";
//        String pythonCommand = "python " + pythonScriptPath;
//
//        // 执行Python脚本
//        Process process = Runtime.getRuntime().exec(pythonCommand);
//
//        // 读取Python脚本的输出
//        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//        StringBuilder output = new StringBuilder();
//        String line;
//        while ((line = reader.readLine()) != null) {
//            output.append(line).append("\n");
//        }
//
//        // 等待Python脚本执行完成
//        try {
//            int exitCode = process.waitFor();
//            if (exitCode == 0) {
//                // Python脚本执行成功
//                return output.toString();
//            } else {
//                // Python脚本执行失败
//                return "Python script execution failed.";
//            }
//        } catch (InterruptedException e) {
//            // 处理中断异常
//            Thread.currentThread().interrupt();
//            return "Python script execution interrupted.";
//        }
//    }
//}
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class PredictController {

    @GetMapping("/runPythonScript")
    public String runPythonScript() {
        try {
            // 使用 ProcessBuilder 执行 Python 脚本
            ProcessBuilder processBuilder = new ProcessBuilder("python", "D:\\个人桌面\\SmartEye\\demo_video.py");
            processBuilder.start();

            return "Python脚本已成功启动。";
        } catch (IOException e) {
            return "执行Python脚本时发生异常: " + e.getMessage();
        }
    }
}