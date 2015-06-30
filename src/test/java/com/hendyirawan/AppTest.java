package com.hendyirawan;

import org.apache.commons.io.FileUtils;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.image.ImageParser;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    private static final Logger log = LoggerFactory.getLogger(AppTest.class);

    @Test
    public void parsePng() throws IOException, TikaException, SAXException {
        final Tika tika = new Tika();
        final byte[] origImage = FileUtils.readFileToByteArray(new File("festival-anak-bertanya-2015_o.png"));
        final String contentType = "image/png";
        final Metadata metadata = new Metadata();
        final Parser parser = tika.getParser(); //won't work: new ImageParser();
        final ParseContext parseContext = new ParseContext();
        final DefaultHandler handler = new DefaultHandler();
        try (final ByteArrayInputStream stream = new ByteArrayInputStream(origImage)) {
            parser.parse(stream, handler, metadata, parseContext);
            log.debug("Image {} metadata: {}", contentType, metadata);

            stream.reset();
            final BufferedImage image = ImageIO.read(stream);
            final int imageIoWidth = image.getWidth();
            final int imageIoHeight = image.getHeight();

            log.info("ImageIO returns: {}×{}", imageIoWidth, imageIoHeight);
            Assert.assertEquals((Integer) imageIoWidth, metadata.getInt(Metadata.IMAGE_WIDTH));
            Assert.assertEquals((Integer) imageIoHeight, metadata.getInt(Metadata.IMAGE_LENGTH));
        }
    }
}
