/*

   Copyright 2000-2004  The Apache Software Foundation 

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */
package org.apache.batik.bridge;

import java.io.InputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.dom.svg.SVGDocumentFactory;
import org.apache.batik.dom.util.DocumentDescriptor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGDocument;

/**
 * This class is responsible on loading an SVG document and
 * maintaining a cache.
 *
 * @author <a href="mailto:Thierry.Kormann@sophia.inria.fr">Thierry Kormann</a>
 * @version $Id$
 */
public class DocumentLoader {

    /**
     * The document factory used to create the document according a
     * DOM implementation.
     */
    protected SVGDocumentFactory documentFactory;

    /**
     * The map that contains the Document indexed by the URI.
     *
     * WARNING: tagged private as no element of this Map should be
     * referenced outise of this class
     */
    protected HashMap cacheMap = new HashMap();

    /**
     * The user agent.
     */
    protected UserAgent userAgent;

    /**
     * Constructs a new <tt>DocumentLoader</tt>.
     */
    protected DocumentLoader() { }

    /**
     * Constructs a new <tt>DocumentLoader</tt> with the specified XML parser.
     * @param userAgent the user agent to use
     */
    public DocumentLoader(UserAgent userAgent) {
        this.userAgent = userAgent;
        documentFactory = new SAXSVGDocumentFactory
            (userAgent.getXMLParserClassName(), true);
	documentFactory.setValidating(userAgent.isXMLParserValidating());
    }

    public Document checkCache(String uri) {
        int n = uri.lastIndexOf('/');
        if (n == -1) 
            n = 0;
        n = uri.indexOf('#', n);
        if (n != -1) {
            uri = uri.substring(0, n);
        }
        DocumentState state = (DocumentState)cacheMap.get(uri);
        if (state != null)
            return state.document;
        return null;
    }

    /**
     * Returns a document from the specified uri.
     * @param uri the uri of the document
     * @exception IOException if an I/O error occured while loading
     * the document
     */
    public Document loadDocument(String uri) throws IOException {
        Document ret = checkCache(uri);
        if (ret != null)
            return ret;

        SVGDocument document = documentFactory.createSVGDocument(uri);

        DocumentDescriptor desc = documentFactory.getDocumentDescriptor();
        DocumentState state = new DocumentState(uri, document, desc);
        cacheMap.put(uri, state);

        return state.document;
    }

    /**
     * Returns a document from the specified uri.
     * @param uri the uri of the document
     * @exception IOException if an I/O error occured while loading
     * the document
     */
    public Document loadDocument(String uri, InputStream is) 
        throws IOException {
        Document ret = checkCache(uri);
        if (ret != null)
            return ret;

        SVGDocument document = documentFactory.createSVGDocument(uri, is);

        DocumentDescriptor desc = documentFactory.getDocumentDescriptor();
        DocumentState state = new DocumentState(uri, document, desc);
        cacheMap.put(uri, state);

        return state.document;
    }

    /**
     * Returns the userAgent used by this DocumentLoader
     */
    public UserAgent getUserAgent(){
        return userAgent;
    }

    /**
     * Disposes and releases all resources allocated by this document loader.
     */
    public void dispose() {
        // new Exception("purge the cache").printStackTrace();
        cacheMap.clear();
    }

    /**
     * Returns the line in the source code of the specified element or
     * -1 if not found.
     *
     * @param e the element
     * @return -1 the document has been removed from the cache or has not
     * been loaded by this document loader.
     */
    public int getLineNumber(Element e) {
        String uri = ((SVGDocument)e.getOwnerDocument()).getURL();
        DocumentState state = (DocumentState)cacheMap.get(uri);
        if (state == null) {
            return -1;
        } else {
            return state.desc.getLocationLine(e);
        }
    }

    /**
     * A simple class that contains a Document and its number of nodes.
     */
    private static class DocumentState {

        private String uri;
        private Document document;
        private DocumentDescriptor desc;

        public DocumentState(String uri,
                             Document document,
                             DocumentDescriptor desc) {
            this.uri = uri;
            this.document = document;
            this.desc = desc;
        }

        public DocumentDescriptor getDocumentDescriptor() {
            return desc;
        }

        public String getURI() {
            return uri;
        }

        public Document getDocument() {
            return document;
        }
    }
}
