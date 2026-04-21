/*
 * Copyright (C) 2026 IDRsolutions
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.idrsolutions.examples;

import org.jpedal.examples.images.ExtractImages;
import org.jpedal.exception.PdfException;

import java.awt.image.BufferedImage;

public class ExtractUnclipped {

    public static void main(final String[] args) {

        // The ExtractImages class is used to extract raw "unclipped" images from a PDF.
        // A "clipped" image includes only the portion of the image that is visible within
        // the PDF's defined clipping region. A clipped image is what you would see when
        // viewing a PDF, whereas an unclipped image is what is actually stored in the
        // file.

        // Initialize a new instance with a file
        final ExtractImages extractImages = new ExtractImages("inputFile.pdf");

        try {

            // Attempt to decode the file
            // This method returns true if the decode was successful and false otherwise
            if (extractImages.openPDFFile()) {

                // Get the number of pages in the document
                final int pageCount = extractImages.getPageCount();

                // PDF page indices start from one, not zero!
                for (int page = 1; page <= pageCount; page++) {

                    // Get the number of images on the current page
                    final int imageCount = extractImages.getImageCount(page);

                    // Get each image on the page as a BufferedImage
                    for (int i = 0; i < imageCount; i++) {

                        // The third parameter "imageAsDisplayed" allows you to choose whether you
                        // want the returned image to have its scaling and rotation applied
                        final BufferedImage image = extractImages.getImage(page, i, true);
                    }
                }
            }

        } catch (final PdfException e) {
            // A PdfException may be thrown if the decoding fails
            e.printStackTrace(System.err);
        } finally {
            // You should always call this to free up system resources
            extractImages.closePDFfile();
        }

    }

}
