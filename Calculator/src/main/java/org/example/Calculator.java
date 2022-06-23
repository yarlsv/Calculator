package org.example;

class Calculator {
    Calculator() {
    }

    double calculate(String str) {
        str = str.replaceAll("\\s", "");
        str = str.replaceAll("[)][(]", ")*(");
        str = str.replaceAll("([0-9])[(]", "$1*(");
        str = str.replaceAll("[)]([0-9])", ")*$1");
        str = this.inBrackets(str);
        str = this.multiplyAndDivision(str);
        str = this.additioonAndSubstraction(str);
        return Double.valueOf(str);
    }

    String inBrackets(String str) {
        StringBuilder bracket = new StringBuilder(str);
        int index = 0;

        for(int i = 0; i < str.length(); ++i) {
            char c = str.charAt(i);
            if (c == '(') {
                index = i;
            } else if (c == ')') {
                String calc = str.substring(index, i + 1);
                String betweenBrackets = calc.substring(1, calc.length() - 1);
                String res = this.multiplyAndDivision(betweenBrackets);
                res = this.additioonAndSubstraction(res);
                int startIndex = bracket.indexOf(calc);
                bracket.replace(startIndex, startIndex + calc.length(), res);
            }
        }

        return bracket.toString();
    }

    String multiplyAndDivision(String str) {
        StringBuilder result = new StringBuilder(str);

        while(true) {
            while(result.indexOf("*") != -1 || result.indexOf("/") != -1) {
                for(int i = 0; i < result.length(); ++i) {
                    char c = result.charAt(i);
                    if (c == '*' || c == '/') {
                        String firstNumber = this.getBefore(result.toString(), i);
                        String secondNumber = this.getAfter(result.toString(), i);
                        String calc = str.substring(i - firstNumber.length(), i + secondNumber.length() + 1);
                        double res = c == '*' ? Double.parseDouble(firstNumber) * Double.parseDouble(secondNumber) : Double.parseDouble(firstNumber) / Double.parseDouble(secondNumber);
                        int startIndex = result.indexOf(calc);
                        result.replace(startIndex, startIndex + calc.length(), String.valueOf(res));
                        break;
                    }
                }
            }

            return result.toString();
        }
    }

    String additioonAndSubstraction(String str) {
        StringBuilder result = new StringBuilder(str);

        while(true) {
            while(result.indexOf("+") != -1 || result.lastIndexOf("-") > 0) {
                for(int i = 0; i < result.length(); ++i) {
                    char c = result.charAt(i);
                    if (c == '+' || c == '-' && i != 0) {
                        String firstNumber = this.getBefore(result.toString(), i);
                        String secondNumber = this.getAfter(result.toString(), i);
                        String calc = str.substring(i - firstNumber.length(), i + secondNumber.length() + 1);
                        double res = c == '+' ? Double.parseDouble(firstNumber) + Double.parseDouble(secondNumber) : Double.parseDouble(firstNumber) - Double.parseDouble(secondNumber);
                        int startIndex = result.indexOf(calc);
                        result.replace(startIndex, startIndex + calc.length(), String.valueOf(res));
                        break;
                    }
                }
            }

            return result.toString();
        }
    }

    String getBefore(String str, int index) {
        StringBuilder result = new StringBuilder("");

        for(int i = index - 1; i >= 0; --i) {
            char c = str.charAt(i);
            if (c == '+' || c == '*' || c == '/' || c == '-' && i != 0) {
                return result.toString();
            }

            result.insert(0, c);
        }

        return result.toString();
    }

    String getAfter(String str, int index) {
        StringBuilder result = new StringBuilder("");

        for(int i = index + 1; i < str.length(); ++i) {
            char c = str.charAt(i);
            if (c == '+' || c == '*' || c == '/' || c == '-') {
                return result.toString();
            }

            result.append(c);
        }

        return result.toString();
    }
}
