package com.ksu.grad.enumerations;

public enum DigestType {
	SHA_1 ("SHA-1"),
	SHA_256 ("SHA-256"),
	RSA ("RSA");
	
	private final String _description;

    private DigestType(String value) {
    	_description = value;
    }

    public String getDescription() {
        return _description;
    }

}
