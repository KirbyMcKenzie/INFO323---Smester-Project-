/*
 *  Created by: Kirby McKenzie | ID:6601021
 *  INFO323 Course Project 2016, Phase 1
 */
package domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kirby McKenzie
 */
@XmlRootElement
public class Webhook {

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Webhook() {
    }

    public Webhook(String url) {
        this.url = url;
    }

} // end class
