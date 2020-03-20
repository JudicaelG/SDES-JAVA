package com.company;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SDES {

    private List<Boolean> masterKey = new LinkedList<>();

    private boolean[][][] s_box1 = {
            {
                    {
                            false, false, true, true
                    },
                    {
                            true, true, false, false
                    },
                    {
                            false, true, false, true
                    },
                    {
                            true, false, true, true
                    }
            },
            {
                    {
                            true, false, true, false
                    },
                    {
                            true, false, true, false
                    },
                    {
                            false, false, true, true
                    },
                    {
                            true, true, true, false
                    }
            }
    };

    private boolean[][][] s_box2 = {
            {
                    {
                            false, false, true, true
                    },
                    {
                            true, false, false, true
                    },
                    {
                            true, false, false, false
                    },
                    {
                            true, false, false, true
                    }
            },
            {
                    {
                            false, true, false, true
                    },
                    {
                            false, false, true, true
                    },
                    {
                            true, false, true, false
                    },
                    {
                            false, true, false, true
                    }
            }
    };

    //private boolean[][][] s_box1 = new boolean[4][4][2];
    //private boolean[][][] s_box2 = new boolean[4][4][2];

    public SDES(String key) {
        for (int i = 0; i < key.length(); i++) {
            if (key.charAt(i) == '1') {
                masterKey.add(Boolean.TRUE);
            } else if (key.charAt(i) == '0') {
                masterKey.add(Boolean.FALSE);
            }
        }
    }

    public List<Boolean> p10(List<Boolean> key) {
        Boolean[] p10Array = new Boolean[10];
        for (int i = 0; i < key.size(); i++) {
            if (i == 0) {
                p10Array[6] = key.get(i);
            } else if (i == 1) {
                p10Array[2] = key.get(i);
            } else if (i == 2) {
                p10Array[0] = key.get(i);
            } else if (i == 3) {
                p10Array[4] = key.get(i);
            } else if (i == 4) {
                p10Array[1] = key.get(i);
            } else if (i == 5) {
                p10Array[9] = key.get(i);
            } else if (i == 6) {
                p10Array[3] = key.get(i);
            } else if (i == 7) {
                p10Array[8] = key.get(i);
            } else if (i == 8) {
                p10Array[7] = key.get(i);
            } else if (i == 9) {
                p10Array[5] = key.get(i);
            }
        }
        return Arrays.asList(p10Array);
    }

    public List<Boolean> p8(List<Boolean> key) {
        Boolean[] p8Array = new Boolean[8];
        for (int i = 0; i < key.size(); i++) {
			/*if (i == 0) {
				p8Array[1] = key.get(i);
			} else if (i == 1) {
				p8Array[3] = key.get(i);
			}*/
            if (i == 2) {
                p8Array[1] = key.get(i);
            } else if (i == 3) {
                p8Array[3] = key.get(i);
            } else if (i == 4) {
                p8Array[5] = key.get(i);
            } else if (i == 5) {
                p8Array[0] = key.get(i);
            } else if (i == 6) {
                p8Array[2] = key.get(i);
            } else if (i == 7) {
                p8Array[4] = key.get(i);
            } else if (i == 8) {
                p8Array[7] = key.get(i);
            } else if (i == 9) {
                p8Array[6] = key.get(i);
            }
        }
        return Arrays.asList(p8Array);
    }

    public List<Boolean> circularLeftShift(List<Boolean> key, int bits) {
        int size = key.size();
        List<Boolean> keyFirstPart = key.subList(0, size/2);
        List<Boolean> keySecondPart = key.subList(size/2,size);

        Collections.rotate(keyFirstPart, -bits);
        Collections.rotate(keySecondPart, -bits);

        List<Boolean> result = new LinkedList<>();
        result.addAll(keyFirstPart);
        result.addAll(keySecondPart);

        return result;
    }

    public List<List<Boolean>> generateKeys() {
        List<Boolean> list10 = p10(masterKey);

        List<Boolean> k1 = p8(circularLeftShift(list10, 1));
        List<Boolean> k2 = p8(circularLeftShift(list10, 3));

        List<List<Boolean>> result = new LinkedList<>(Collections.singleton(k1));
        result.add(k2);
        return result;
    }

    public List<Boolean> ip(List<Boolean> plainText) {
        Boolean[] p8Array = new Boolean[8];
        for (int i = 0; i < plainText.size(); i++) {
            if (i == 0) {
                p8Array[3] = plainText.get(i);
            } else if (i == 1) {
                p8Array[0] = plainText.get(i);
            } else if (i == 2) {
                p8Array[2] = plainText.get(i);
            } else if (i == 3) {
                p8Array[4] = plainText.get(i);
            } else if (i == 4) {
                p8Array[6] = plainText.get(i);
            } else if (i == 5) {
                p8Array[1] = plainText.get(i);
            } else if (i == 6) {
                p8Array[7] = plainText.get(i);
            } else if (i == 7) {
                p8Array[5] = plainText.get(i);
            }
        }
        return Arrays.asList(p8Array);
    }

    public List<Boolean> rip(List<Boolean> permutedText) {
        Boolean[] p8Array = new Boolean[8];
        for (int i = 0; i < permutedText.size(); i++) {
            if (i == 0) {
                p8Array[1] = permutedText.get(i);
            } else if (i == 1) {
                p8Array[5] = permutedText.get(i);
            } else if (i == 2) {
                p8Array[2] = permutedText.get(i);
            } else if (i == 3) {
                p8Array[0] = permutedText.get(i);
            } else if (i == 4) {
                p8Array[3] = permutedText.get(i);
            } else if (i == 5) {
                p8Array[7] = permutedText.get(i);
            } else if (i == 6) {
                p8Array[4] = permutedText.get(i);
            } else if (i == 7) {
                p8Array[6] = permutedText.get(i);
            }
        }
        return Arrays.asList(p8Array);
    }

    public List<Boolean> ep(List<Boolean> permutedText) {
        Boolean[] p8Array = new Boolean[8];
        for (int i = 0; i < permutedText.size(); i++) {
            if (i == 0) {
                p8Array[1] = permutedText.get(i);
                p8Array[7] = permutedText.get(i);
            } else if (i == 1) {
                p8Array[2] = permutedText.get(i);
                p8Array[4] = permutedText.get(i);
            } else if (i == 2) {
                p8Array[3] = permutedText.get(i);
                p8Array[5] = permutedText.get(i);
            } else if (i == 3) {
                p8Array[0] = permutedText.get(i);
                p8Array[6] = permutedText.get(i);
            }
        }
        return Arrays.asList(p8Array);
    }

    public List<Boolean> xor(List<Boolean> a, List<Boolean> b) {
        Boolean[] p8Array = new Boolean[a.size()];
        for (int i = 0; i < a.size(); i++) {
            p8Array[i] = a.get(i) ^ b.get(i);
        }
        return Arrays.asList(p8Array);
    }

    public List<Boolean> sandBox(boolean[][][] sandBox, List<Boolean> list){
        //Calculation coordinate x
        int x = calculationCoordinate(list.get(0), list.get(3));
        //Calculation coordinate y
        int y = calculationCoordinate(list.get(1), list.get(2));
        List<Boolean> result = new LinkedList<>();
        result.add(sandBox[0][x][y]);
        result.add(sandBox[1][x][y]);
        return result;
    }

    public int calculationCoordinate(Boolean a, Boolean b) {
        if (a.equals(b) && a.equals(false)) {
            return 0;
        } else if (a.equals(false) && b.equals(true)) {
            return 1;
        } else if (a.equals(true) && b.equals(false)) {
            return 2;
        } else {
            return 3;
        }
    }

    public List<Boolean> p4(List<Boolean> a, List<Boolean> b) {
        Boolean[] p4Array = new Boolean[4];
        p4Array[0] = a.get(1);
        p4Array[1] = b.get(1);
        p4Array[2] = b.get(0);
        p4Array[3] = a.get(0);
        return Arrays.asList(p4Array);
    }

    public List<Boolean> f(List<Boolean> right, List<Boolean> sk) {
        List<Boolean> eped = ep(right);
        List<Boolean> xored = xor(eped, sk);
        List<Boolean> firstPart = xored.subList(0, 4);
        List<Boolean> secondPart = xored.subList(4,8);

        return p4(sandBox(s_box1, firstPart),sandBox(s_box2, secondPart));
    }

    public List<Boolean> fk(List<Boolean> bits, List<Boolean> key){
        List<Boolean> halfResult;
        halfResult = xor(bits.subList(0,4), f(bits.subList(4,8), key));
        List<Boolean> result = new LinkedList<>();
        result.addAll(halfResult);
        result.addAll(bits.subList(4,8));
        return result;
    }

    public List<Boolean> sw(List<Boolean> input) {
        return circularLeftShift(input, 4);
    }

    public char encrypt(char block) {
        List<List<Boolean>> ks = generateKeys();
        List<Boolean> ip = ip(charToBits(block));
        List<Boolean> fk = fk(ip, ks.get(0));
        List<Boolean> sw = sw(fk);
        List<Boolean> fk2 = fk(sw, ks.get(1));
        List<Boolean> rip = rip(fk2);
        return bitsToChar(rip);
    }

    public char decrypt(char block) {
        List<List<Boolean>> ks = generateKeys();
        List<Boolean> ip = ip(charToBits(block));
        List<Boolean> fk = fk(ip, ks.get(1));
        List<Boolean> sw = sw(fk);
        List<Boolean> fk2 = fk(sw, ks.get(0));
        List<Boolean> rip = rip(fk2);
        return bitsToChar(rip);
    }

    public char bitsToChar(List<Boolean> list) {
        int ascii = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(true) && i == 0) {
                ascii += 128;
            }
            if (list.get(i).equals(true) && i == 1) {
                ascii += 64;
            }
            if (list.get(i).equals(true) && i == 2) {
                ascii += 32;
            }
            if (list.get(i).equals(true) && i == 3) {
                ascii += 16;
            }
            if (list.get(i).equals(true) && i == 4) {
                ascii += 8;
            }
            if (list.get(i).equals(true) && i == 5) {
                ascii += 4;
            }
            if (list.get(i).equals(true) && i == 6) {
                ascii += 2;
            }
            if (list.get(i).equals(true) && i == 7) {
                ascii += 1;
            }
        }
        return (char) ascii;
    }

    public List<Boolean> charToBits(char block) {
        int valueAscii = block;
        Boolean[] char8 = new Boolean[8];
        if (valueAscii/128 >= 1) {
            char8[0] = true;
            valueAscii -= 128;
        } else {
            char8[0] = false;
        }
        if (valueAscii/64 >= 1) {
            char8[1] = true;
            valueAscii -= 64;
        } else {
            char8[1] = false;
        }
        if (valueAscii/32 >= 1) {
            char8[2] = true;
            valueAscii -=32;
        } else {
            char8[2] = false;
        }
        if (valueAscii/16 >= 1) {
            char8[3] = true;
            valueAscii -=16;
        } else {
            char8[3] = false;
        }
        if (valueAscii/8 >= 1) {
            char8[4] = true;
            valueAscii -=8;
        } else {
            char8[4] = false;
        }
        if (valueAscii/4 >= 1) {
            char8[5] = true;
            valueAscii -=4;
        } else {
            char8[5] = false;
        }
        if (valueAscii/2 >= 1) {
            char8[6] = true;
            valueAscii -=2;
        } else {
            char8[6] = false;
        }
        if (valueAscii >= 1) {
            char8[7] = true;
            valueAscii -=1;
        } else {
            char8[7] = false;
        }
        return Arrays.asList(char8);
    }
}
