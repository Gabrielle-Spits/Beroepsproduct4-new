package com.example.beroepsproduct4;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import com.example.beroepsproduct4.model.Oudergegevens;
import com.example.beroepsproduct4.model.Rollatorgegevens;
import com.example.beroepsproduct4.model.Rollatorhoortbij;
import com.example.beroepsproduct4.model.Zorgcentrum;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    /**
     * Unit test to test to zorcentrum in class Zorgcentrum
     */
    @Test
    public void Zorcentrum_zorgcentrum(){
        Zorgcentrum z = new Zorgcentrum();
        z.setZorgcentrum("test");
        assertEquals("test",z.getZorgcentrum());
    }


    /**
     * Unit test to test to oudernaam in class Oudergegevens
     */
    @Test
    public void Oudergegevens_oudernaam() {
        Oudergegevens o = new Oudergegevens();
        o.setOudernaam("Piet");
        assertEquals("Piet",o.getOudernaam());
    }

    /**
     * Unit test to test to bsn in class Rollatorhoortbij
     */
    @Test
    public void Rollatorhoortbij_oudergegevens(){
        Oudergegevens o = new Oudergegevens();
        o.setBsn("bsn");
        Rollatorhoortbij r = new Rollatorhoortbij();
        r.setOudergegevens(o);
       assertEquals(o,r.getOudergegevens().getBsn());

    }

    /**
     * Intergratie test van klasse Zorgcentrum en Regressie test van klasse Oudergegevnes
     */
    @Test
    public void TestZorgcentrumOudergegevens(){
        Zorgcentrum z = new Zorgcentrum();
        z.setZorgcentrum("Marienstein");
        z.setAfdeling("A");
        assertEquals("Marienstein", z.getZorgcentrum());
        assertEquals ("A", z.getAfdeling());

        Oudergegevens o = new Oudergegevens();
        o.setZorgcentrum(z);
        assertEquals (z, o.getZorgcentrum());
    }

    /**
     * Intergratie test van klasse Oudergegevens en Regressie test van klasse Rollatorhoortbij
     */
    @Test
    public void TestOudergegevensRollatorHoortbij(){
        Oudergegevens o = new Oudergegevens();
        o.setBsn("123456789");
        assertEquals ("123456789", o.getBsn());

        Rollatorhoortbij r = new Rollatorhoortbij ();
        r.setOudergegevens(o);
        assertEquals (o, r.getOudergegevens());

    }

    /**
     * Intergratie test van klasse RollatorHoortbij en Regressie test van klasse Rollatorgegevens
     */
    @Test
    public void TestRollatorHoortbijRollatorgegevens(){
        Rollatorhoortbij r = new Rollatorhoortbij();
        r.setRollator("Rollator1");
        assertEquals ("Rollator1", r.getrollator());

        Rollatorgegevens rg = new Rollatorgegevens();
        rg.setRollator(r);
        assertEquals (r,rg.getRollator());

    }


}