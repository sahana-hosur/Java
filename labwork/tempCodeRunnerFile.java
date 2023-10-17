 private void performPowerSet(Set<String> A) {
        Set<Set<String>> powerSet = new HashSet<>();
        generatePowerSet(A.toArray(new String[0]), 0, new HashSet<>(), powerSet);
        displayResult("Power Set:", powerSet);
    }

    private void generatePowerSet(String[] setArray, int index, Set<String> current, Set<Set<String>> powerSet) {
        if (index == setArray.length) {
            powerSet.add(new HashSet<>(current));
            return;
        }
        generatePowerSet(setArray, index + 1, current, powerSet);
        current.add(setArray[index]);
        generatePowerSet(setArray, index + 1, current, powerSet);
        current.remove(setArray[index]);
    }