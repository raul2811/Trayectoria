#include <iostream>

void printStyledText(const std::string& text) {
    std::cout << "\x1B[1m" << text << "\x1B[0m";
}

int main() {
    printStyledText(R"(
█▀▀█ ░░ 　 ▒█▀▀▀█ █▀▀█ █░░ ░▀░ █▀▀█ 
░░▀▄ ▀▀ 　 ░▀▀▀▄▄ █▄▄█ █░░ ▀█▀ █▄▄▀ 
█▄▄█ ░░ 　 ▒█▄▄▄█ ▀░░▀ ▀▀▀ ▀▀▀ ▀░▀▀)" << std::endl;

    // Otros cout en el estilo deseado
    printStyledText("¡Hola, mundo!");
    printStyledText("Este es un texto estilizado.");

system("pause");
    return 0;
}