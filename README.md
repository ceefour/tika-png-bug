## Tika ImageParser PNG bug

Problem: Metadata.IMAGE_WIDTH and Metadata.IMAGE_LENGTH is not given for PNG files.

    21:40:16.057 [main] DEBUG com.hendyirawan.AppTest - Image image/png metadata: 
    21:40:16.301 [main] INFO  com.hendyirawan.AppTest - ImageIO returns: 600×844
    
    java.lang.AssertionError: 
    Expected :600
    Actual   :null
     <Click to see difference>
        at org.junit.Assert.fail(Assert.java:88)
        at org.junit.Assert.failNotEquals(Assert.java:834)
        at org.junit.Assert.assertEquals(Assert.java:118)
        at org.junit.Assert.assertEquals(Assert.java:144)
        at com.hendyirawan.AppTest.parsePng(AppTest.java:47)
