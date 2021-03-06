package ch.so.agi.oereb.pdf4oereb.saxon.ext;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.XdmAtomicValue;
import net.sf.saxon.s9api.XdmNode;
import net.sf.saxon.tree.util.Orphan;
import net.sf.saxon.type.Type;

public class FixImageTest {
    Logger log = LoggerFactory.getLogger(FixImageTest.class);

    @Test
    // Apache FOP seems to have problems with 8bit (paletted images).
    public void fixEmbeddedSymbol_Ok() throws SaxonApiException {
		Processor processor = new Processor(false);

        String badSymbolString = "iVBORw0KGgoAAAANSUhEUgAAAEgAAAAkCAMAAAAdBYxUAAAB9VBMVEX8RiJWFwowDQZWFwtjGw2VKRSGJRJbGAuPJxPMORszDQbwQiDnQB/wQiFrHQ7IOBvTOhw2DgfYPB3rQh+AIxDkPx4vDAb4RSHbPR5PFQqcKxTZPB0SBQJ3IQ9yHw/BNRoGAQBAEgjDNhptHg4FAQDxQyEzDgaxMRgyDQY3DgcWBgMHAgBRFQpsHQ7FNhrtQh8EAQBBEggyDAVtHQ4pCwVEEggJAgHzQyEVBgIsCwQYBgMqCwS7MxiYKhTePR5NFAg1DgVQFQryQyEoCgQvDAXqQB/UOhwTBQI4DwdJFAhCEQdVFwoHAgFTFwo3DgVLFAjfPR4MAwEsCwSlLhbdPR5OFQgqCwQRBAJoHA4fCAREEwhXFwtJFAk8EAczDgUnCgVIEwi8NRlUFwqZKhQsDAZvHw5iGw17Ig82DgZmHA0IAgENAwEqCwUKAgFQFQoQBAIrCwRnHA1PFQgcCAPdPR1CEghWFgpIFAk5DwcyDAUlCgVIEgifKxUUBQLCNhrlPx+KJhKCIxCRKBMzDgbINxswDAW/NRgUBQKwMRcXBgIZBwMrCwRRFgoLAwEsDAUPBAIHAQBlHA03Dwd8IhBgGgtvHg4xDQabKxRSFgrANRpGEggpCwU0DgU9EAdLFAlaGAtFEwglCQRpHA0QBAIBAAADAAAAAADlALDwAAAApHRSTlPNxfTs6d/i6+DV88/Rz+fW1PPT0OPR9M7T7d7T++Xm1/7w1+f+z6Pa9PL6/sjn1tD+8Nvn9s/9z/rd+d/Y39LK2MjP4NzQ1PvyzNDG/sfXy9L83tzTyuD76Pjw6+7x2fbN2Oze9ebp5PLo/fz1/e373+jJ+NPw7O/y2vfN3frX0eHj4PPX9Nj72vr53u399fz+6fLk6uf03u3XzvXZ8e7r8Pbo/IbrDpEAAAK0SURBVEiJrdbnV1MxFADwpIhKtbVaJ69qJ8WWDilgURCUDQIy3Xvvvffee+9V+neam5u8QZ4fPH3vQ/NyT97vNMm9OSFVlD+NLdgOtmNLh8ew7akVgSensb1ySQRWrcG2/iv7qSKUwLNkMW/Iwvnz8MU7F9s5rpn4MmUqtj6XH1+mTce2wj2D/VKESnYEVLqDkAMOqWeQE05ZK6GOOOWUNDrisIm1THYONG/Z0ZWN/jn28UNGOunInUSwkHdHwv9w5PYbzu/NXUX9aUBHO2eElmu2joR0Z/+movnhTlPAHEqm7BwB6c6pMzD28MaR56OdzRcmivz/cGdopKPj4UV4C2g2DkLG+hyEkYkKsc6+EKxPCGIbcJ2f8tnZOBwynH1JNm5il2W/wvBt9iwGzg9BL606s8YJNe37Gxi21brvOYi9kvvuhV5EdWbXkEFT/nTDsJvW/KmGWFw4JA69o6rjoaTdlIcrYVidNQ/zEFsvHLIaelHV0fMI83kdDBu1OCQKsbXCITHoBVVHh7AuOOSxOL6CgDAPOZRUHQmJ+uJT0yyO6wFOTeQzn1pCdQQk65Qv7FWL43/PF1vWxW3ouVUHIb3eH8Ow79Z6T/ENkPX1iG+/6izoZZBxbtwNQkJ+spwbPCGPZNC5dw16YdVZNECo+fw5BOOevRNO3TdoeIns5o77OJaI6lRSMmw+x2J8lRq81/2ezlsvWNGyR3sJsW1x//1f3Vi0Ng6b2JjJIeTG60nHCNv3lOUYCTTZOXoe6fX19qfpMzjY2H5pISMUsv0/OmSu01h52eVAtnBi7489GZmH6dyXaDCYr87ZrXOlvv3/ec7bOQg54JA+BjnhLG0j1BFnWT/pccRZQUmtI45xsJXoMAavfjvlBe+kuM993i4veDXigtcqAuMi0DuAbV9bP1z9/gJpFg+vX350qQAAAABJRU5ErkJggg==";
		Orphan node = new Orphan(processor.getUnderlyingConfiguration());
		node.setNodeKind(Type.TEXT);
		node.setStringValue(badSymbolString);
		XdmNode badSymbolStringNode = new XdmNode(node);

		XdmNode[] arguments = {badSymbolStringNode};
		FixImage fixImage = new FixImage();
        XdmAtomicValue resultSymbol = (XdmAtomicValue) fixImage.call(arguments);
        
        String expectedResult = "iVBORw0KGgoAAAANSUhEUgAAAEgAAAAkCAYAAAAq23xmAAAFWElEQVR42u2aTWwbRRTHba9319m1N47t2EmcxCQoSutGkDRNnKJUmLSlRRW0hC8JLgiJD9FGlbhwgAPiwIEL4oAEAokD4ow4gwQItQRom9CWxqTNB5A2bZOA2zShNB8wf2MbZ3Zmd+1uDog9jBTtzDzN/Dzz3v+9jGuHJn30WEw5xmodfmHiwYjvO17/gYjv+06/eIHXn7OhCpMP1fJt7I/IJ7b7xfNGNpLExsGo71uujRr5JLExbmJj6lBEGeb17wnJIzs08afSb2Djwh9r97eepNtLieCll1u0i6w+tPP9zWfuVqUbM/cmfuCNOdykzb52Z/BXXn+mv+lMZ0C6MWtg45l44MobbaFfeP2n72k8CxtzA4lR3pin6tS5t9rD07z+Ezsbf+wOSIvZ3XeMlH4HGyYgB44BIAeOASAHzsYGv1QE5MDZ2B6PqfM9mpjJAXLg6OG8szU8lbtiCOUOHD2cog+CzrldOK+0Vs+8mwxPPRFT59oVcTksela8bte6SFrQ61lVPO61waiy8H4yMrnA2CAN51hvw7mhZm22W5MWo5JwSyJ23C7XX7WS5xY2iL6vUw1jmwnHMMxbhfNiPHB5T9iXBRAX2YCVVkVg8eCMk5M2EPJlrdq6j4zFnM2AYwjIChxsLOHz3rS6mdLGgvNZd10mVAboQpM9rvUj5BTbDYcLyAqc58nJafQJOjg+cjpeiGuXP74reqHDLy5NEFtnie/A9dsXqfpdKAFEnxwWHL/gXoWtq+nEKNonXbHxndXydXoc5sKGnXCYgKz6nF5N0i0SvgILMnLIcLRpci1on5NmXKsar2eF55AfJv6Mdd3shKMDZBXO0YR2Cce6dHE4GZ/31GcqiVZwtvRm4eAnGCeiuLGoMo/TRc+Dc7cLznBf/FxSFadzgMoJ5a+2BmfohT0Qqfqt0lA+ROzS9p5tDFwxC+WHGfMQ3eyC06vJi4Mk+3ehZFGOzulj+IA320M/V6pztpN5tL1Pu+rHzXQOxtDzyC++ZBec7O6WkdwVQz2nHBFYJwl/0gvbqorLlYpAaBva3tSu5tNmInCSjKHnQXfZBcdUB/EUsuxxr9MLQ7SqVCFjU7S9a/kFGolAjKHnQVDaBccUEC99YAHKUhsqJ31wM7RNKSCeCGQBQvCwC44hIKPcinXFWBrEam4VYeifwhUzUsisKwapYRccLiCzxJPlVD/cVjtRaeLZxXHSZunD21vC0/Q8OGi74DABWcnK62X9CTpAwnylWfkRRriG0zfLrWIM544wbxecm3tbTg3UyKNFQFZLFsOp+Jjk1gvFL3saxiopWbCEYoCIwAWOigacTlXE1dQBKmT4dsA5SNR6ShPHcoDKrec8R3IxenGq4F472sxPGgEHJ2NXUL5G97FSjUdjyjwvtzpkkGrYBeeDZGTyn4KZKkyWW+xCBGH5IpQyABu/JE4JFoFk9XViv9rrWRWobL7Q4ORlRrjvJzDhj2Dri566TFuVd7nPIFm1E07RB+GfepVUAi+SbynGYsspd5SGcviiSsodmINSid1wTHWQldzq6Qb16t6wLxu6jYJZabTCKUiXUTBL5wtmmwHHEFC5iSeuHBaIhbYp4h+hfMkVIjDo9ax0+KWlJwmI96iSKy+U50qu5EQRn7UEpS3lG9ISXO2hpn9LrpsFhwvov1pgtxsOE5ADRwfoeBGQA2dj+4roui1KvmDmwNHDSWny9UdiyjcuvM9x4OjhLBYKZni85MDRw7FUMPs/wykC4j3B2x/OPWszfBq3zeRZ276wfKqbetbGeho3aPI0Dq8sjG2I00Y2kJUj8TSwcRwOGT6HfoL3N8eSeRF8oyVvAAAAAElFTkSuQmCC";
        
        assertEquals(expectedResult, resultSymbol.getStringValue(), "Fixed symbol is not equal.");        
    }
}
