// Hasher.java - code for hashing class
// Author: ?????
// Date:   ?????
// Class:  CS165
// Email:  ?????

@FunctionalInterface
interface HashFunction {
    int hash(String key);
}


public class Hasher {

    // Hashing algorithms, see specification

    /**
     * Hashing algorithms, see provided documentation in assignment
     * @param hashFunction FIRST, SUM, PRIME, OR JAVA
     * @return the corresponding HashFunction
     */
    public static HashFunction make(String hashFunction) {
        switch (hashFunction) {
        case "FIRST":
            return new HashFunction() {
            	@Override
            	public int hash(String key) {
            		return Character.getNumericValue(key.charAt(0));
            	}
            };

        case "SUM":
            return new sumHash();

        case "PRIME":
            return (String key) -> {
            	int prime = 7;
            	for (int i = 0; i < key.length(); i++) {
            		prime = prime * 31 + Character.getNumericValue(key.charAt(i));
            	}
            	return prime;
            };

        case "JAVA":
            return (String key) -> {
            	return key.hashCode();
            };


        default:
            usage();
        }
        return null;
    }
    
    public static class sumHash implements HashFunction {
    	@Override
    	public int hash(String key) {
    		int sum = 0;
    		for (int i = 0; i < key.length(); i++) {
    			sum += Character.getNumericValue(key.charAt(i));
    		}
    		return sum;
    	}
    }


    // Usage message
    private static void usage() {
        System.err.println("Usage: java Hasher <FIRST|SUM|PRIME|JAVA> <word>");
        System.exit(1);
    }



    // Test code for hasher
    public static void main(String[] args) {
        args = Debug.init(args);
        if (args.length != 2)
            usage();

        HashFunction sh = make(args[0]);
        int hashCode = sh != null ? sh.hash(args[1]) : 0;
        System.out.printf("'%s' hashes to %d using %s\n", args[1], hashCode, args[0]);
    }
}
