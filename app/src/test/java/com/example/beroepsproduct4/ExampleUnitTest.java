package com.example.beroepsproduct4;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import com.example.beroepsproduct4.model.Oudergegevens;
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
    @Test
    public void Zorcentrum_zorgcentrum(){
        Zorgcentrum z = new Zorgcentrum();
        z.setZorgcentrum("test");
        assertEquals("test",z.getZorgcentrum());
    }

    @Test
    public void Oudergegevens_oudernaam() {
        Oudergegevens o = new Oudergegevens();
        o.setOudernaam("Piet");
        assertEquals("Piet",o.getOudernaam());
    }

    @Test
    public void Rollatorhoortbij_oudergegevens(){
        Oudergegevens o = new Oudergegevens();
        o.setBsn("bsn");
        Rollatorhoortbij r = new Rollatorhoortbij();
        r.setOudergegevens(o);
       assertEquals(o,r.getOudergegevens().getBsn());

    }


}