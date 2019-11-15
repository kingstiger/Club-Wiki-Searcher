
package UrlResults;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "pageid",
    "ns",
    "title",
    "contentmodel",
    "pagelanguage",
    "pagelanguagehtmlcode",
    "pagelanguagedir",
    "touched",
    "lastrevid",
    "length",
    "fullurl",
    "editurl",
    "canonicalurl"
})
public class SingleUrlInfo {

    @JsonProperty("pageid")
    private Integer pageid;
    @JsonProperty("ns")
    private Integer ns;
    @JsonProperty("title")
    private String title;
    @JsonProperty("contentmodel")
    private String contentmodel;
    @JsonProperty("pagelanguage")
    private String pagelanguage;
    @JsonProperty("pagelanguagehtmlcode")
    private String pagelanguagehtmlcode;
    @JsonProperty("pagelanguagedir")
    private String pagelanguagedir;
    @JsonProperty("touched")
    private String touched;
    @JsonProperty("lastrevid")
    private Integer lastrevid;
    @JsonProperty("length")
    private Integer length;
    @JsonProperty("fullurl")
    private String fullurl;
    @JsonProperty("editurl")
    private String editurl;
    @JsonProperty("canonicalurl")
    private String canonicalurl;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("pageid")
    public Integer getPageid() {
        return pageid;
    }

    @JsonProperty("pageid")
    public void setPageid(Integer pageid) {
        this.pageid = pageid;
    }

    @JsonProperty("ns")
    public Integer getNs() {
        return ns;
    }

    @JsonProperty("ns")
    public void setNs(Integer ns) {
        this.ns = ns;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("contentmodel")
    public String getContentmodel() {
        return contentmodel;
    }

    @JsonProperty("contentmodel")
    public void setContentmodel(String contentmodel) {
        this.contentmodel = contentmodel;
    }

    @JsonProperty("pagelanguage")
    public String getPagelanguage() {
        return pagelanguage;
    }

    @JsonProperty("pagelanguage")
    public void setPagelanguage(String pagelanguage) {
        this.pagelanguage = pagelanguage;
    }

    @JsonProperty("pagelanguagehtmlcode")
    public String getPagelanguagehtmlcode() {
        return pagelanguagehtmlcode;
    }

    @JsonProperty("pagelanguagehtmlcode")
    public void setPagelanguagehtmlcode(String pagelanguagehtmlcode) {
        this.pagelanguagehtmlcode = pagelanguagehtmlcode;
    }

    @JsonProperty("pagelanguagedir")
    public String getPagelanguagedir() {
        return pagelanguagedir;
    }

    @JsonProperty("pagelanguagedir")
    public void setPagelanguagedir(String pagelanguagedir) {
        this.pagelanguagedir = pagelanguagedir;
    }

    @JsonProperty("touched")
    public String getTouched() {
        return touched;
    }

    @JsonProperty("touched")
    public void setTouched(String touched) {
        this.touched = touched;
    }

    @JsonProperty("lastrevid")
    public Integer getLastrevid() {
        return lastrevid;
    }

    @JsonProperty("lastrevid")
    public void setLastrevid(Integer lastrevid) {
        this.lastrevid = lastrevid;
    }

    @JsonProperty("length")
    public Integer getLength() {
        return length;
    }

    @JsonProperty("length")
    public void setLength(Integer length) {
        this.length = length;
    }

    @JsonProperty("fullurl")
    public String getFullurl() {
        return fullurl;
    }

    @JsonProperty("fullurl")
    public void setFullurl(String fullurl) {
        this.fullurl = fullurl;
    }

    @JsonProperty("editurl")
    public String getEditurl() {
        return editurl;
    }

    @JsonProperty("editurl")
    public void setEditurl(String editurl) {
        this.editurl = editurl;
    }

    @JsonProperty("canonicalurl")
    public String getCanonicalurl() {
        return canonicalurl;
    }

    @JsonProperty("canonicalurl")
    public void setCanonicalurl(String canonicalurl) {
        this.canonicalurl = canonicalurl;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
