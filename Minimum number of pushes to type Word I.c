int minimumPushes(char* word) {
    int len = 0;
    while (word[len] != '\0') {
        len++;
    }
    int total = 0;
    for (int i = 0; i < len; i++) {
        total += (i / 8) + 1;
    }
    return total;
}
