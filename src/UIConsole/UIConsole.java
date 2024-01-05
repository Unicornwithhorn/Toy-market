package UIConsole;

import FileWork.FileRecordSet;

import java.util.Objects;
import java.util.Scanner;

public class UIConsole {
    public void start() {
        FileRecordSet fileRecordSet = new FileRecordSet();
        System.out.println("Введите следующие данные в произвольном порядке, разделенные пробелом:\n" +
                "Фамилия, Имя, Отчество, Дата рождения, Номер телефона, Пол" +
                "Форматы данных:\n" +
                "фамилия, имя, отчество - строки\n" +
                "датарождения - строка формата dd.mm.yyyy\n" +
                "номертелефона - целое беззнаковое число без форматирования\n" +
                "пол - символ латиницей f или m.");
        Scanner scanner = new Scanner(System.in);
        String[] inputData = scanner.nextLine().split(" ");
        if (inputData.length != 6){
            throw new IllegalArgumentException("количество введённых данных - " + inputData.length +
                    " (требуется 6)");
        }
        for (int i = 0; i < 6; i++) {
            checkDataTypes(inputData[i], fileRecordSet);
        }
        if (fileRecordSet.getDate() == null){
            throw new NullPointerException("Дата рождения не введена или введена некорректно");
        } else if (fileRecordSet.getPhone() == null) {
            throw new NullPointerException("Номер телефона не введен или введен некорректно");
        } else if (fileRecordSet.getGender() == null) {
            throw new NullPointerException("Пол пользователя не введен или введен некорректно");
        } else if (fileRecordSet.getPatronic() == null) {
            throw new NullPointerException("Отчество не введено или введено некорректно");
        }
        fileRecordSet.recordSet();
    }

    private void checkDataTypes(String inputData, FileRecordSet fileRecordSet) {
        if (checkBirthDate(inputData, fileRecordSet)){ return;}
        if (checkPhone(inputData, fileRecordSet)){return;}
        if (checkGender(inputData, fileRecordSet)){return;}
        if (checkPatronim(inputData, fileRecordSet)){return;}
        choiceSurnameOrName(inputData, fileRecordSet);
    }

    private void choiceSurnameOrName(String inputData, FileRecordSet fileRecordSet){
        if (fileRecordSet.getSurname() == null){
            fileRecordSet.setSurname(inputData);
        } else {
            fileRecordSet.setName(inputData);
        }
    }

    private boolean checkPatronim(String inputData, FileRecordSet fileRecordSet) {
        String[] data = inputData.split("");
        if (data.length > 3){
            String endingOne = data[data.length-2] + data[data.length-1];
            String endingTwo =  data[data.length-4] + data[data.length-3] + endingOne;
            if (endingOne.equals("ич") ||
                    endingTwo.equals("овна") ||
                    endingTwo.equals("евна") ||
                    endingTwo.equals("ична")) {
                fileRecordSet.setPatronic(inputData);
                return true;
            }
        }
        return false;
    }

    private boolean checkGender(String inputData, FileRecordSet fileRecordSet) {
        if (Objects.equals(inputData, "f") || Objects.equals(inputData, "m")){
            fileRecordSet.setGender(inputData);
            return true;
        }
        return false;
    }

    private boolean checkPhone(String inputData, FileRecordSet fileRecordSet) {
        if(checkNumber(inputData)){
            fileRecordSet.setPhone(inputData);
            return true;
        }
        return false;
    }


    private boolean checkNumber(String inputData){
        try {
            Integer.parseInt(inputData);
            return true;
        } catch (NumberFormatException E){
            return false;
        }
    }

    private boolean checkBirthDate(String inputData, FileRecordSet fileRecordSet){
        String[] birthDateMaybe = inputData.split("\\.");
        if (birthDateMaybe.length == 3){
            for (String string: birthDateMaybe){
                if (!checkNumber(string)){
                    return false;
                }
            }
            fileRecordSet.setDate(inputData);
            return true;
        }
        return false;
    }
}

