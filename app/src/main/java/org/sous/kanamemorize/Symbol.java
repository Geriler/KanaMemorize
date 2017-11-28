package org.sous.kanamemorize;

class Symbol {
    private String value; //значение на японском
    private String transcription; //транскрипция
    private Integer degree; //степень изученности 0..5
    private static int MAX_DEGREE = 5; //максимальное значение изученности
    private static int MIN_DEGREE = 0; //минимальное значение изученности

    Symbol(String value, String transcription) {
        this.value = value;
        this.transcription = transcription;
        degree = MIN_DEGREE;
    }

    //проверка правильности транскрипции
    boolean checkTranscription (String transcription) {
        if (this.transcription.equals(transcription)) {
            increaseDegree();
            return true;
        }
        else {
            decreaseDegree();
            return  false;
        }
    }

    //проверка завершения изучения символа
    boolean isComplete () {
        return degree == MAX_DEGREE;
    }

    //добавление изученности
    private void increaseDegree() {
        if (degree < MAX_DEGREE) {
            degree++;
        }
    }

    //уменьшение изученности
    private void decreaseDegree() {
        if (degree > MIN_DEGREE) {
            degree--;
        }
    }

    //получение значения символа
    String getValue() {
        return value;
    }
}