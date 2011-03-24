/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kg.apc.jmeter.samplers;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author APC
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({UDPSamplerGuiTest.class, DNSJavaDecoderTest.class, HexStringUDPDecoderTest.class, DummySamplerGuiTest.class, HTTPRawSamplerTest.class, SocketChannelWithTimeoutsTest.class, DatagramChannelWithTimeoutsTest.class, DummySamplerTest.class, HTTPRawSamplerGuiTest.class, UDPTrafficDecoderTest.class, AbstractIPSamplerTest.class, UDPSamplerTest.class})
public class SamplersSuite {

   @BeforeClass
   public static void setUpClass() throws Exception
   {
   }

   @AfterClass
   public static void tearDownClass() throws Exception
   {
   }

   @Before
   public void setUp() throws Exception
   {
   }

   @After
   public void tearDown() throws Exception
   {
   }

}