package org.apache.camel.openstack.Model;

/**
 * Created by EKabardinsky on 4/26/2016.
 */
public class CreateVMInfo {
    private String image;
    private String flavor;
    private String name;
    private String keyPair;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeyPair() {
        return keyPair;
    }

    public void setKeyPair(String keyPair) {
        this.keyPair = keyPair;
    }
}
