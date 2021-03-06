package org.aion.valid;

import org.aion.base.type.IBlockHeader;
import org.aion.mcf.valid.BlockNumberRule;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link BlockNumberRule}.
 *
 * @author Alexandra Roatis
 */
public class BlockNumberRuleTest {

    @Mock
    IBlockHeader mockChildBH;
    @Mock
    IBlockHeader mockParentBH;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Checks if the {@link BlockNumberRule#validate} returns {@code true} when
     * the block number is <b>smaller by 1</b> than the parent block number.
     */
    @Test
    public void testValidateWithParent() {
        // define return value for method getNumber()
        when(mockChildBH.getNumber()).thenReturn(661988L);
        when(mockParentBH.getNumber()).thenReturn(661987L);

        // generate output
        boolean actual = new BlockNumberRule<>().validate(mockChildBH, mockParentBH);

        // test output
        assertThat(actual).isTrue();
    }

    /**
     * Checks if the {@link BlockNumberRule#validate} returns {@code false} when
     * the block number is <b>larger</b> than the parent block number.
     */
    @Test
    public void testValidateWithSmallerParentNumber() {
        // define return value for method getNumber()
        when(mockChildBH.getNumber()).thenReturn(661987L);
        when(mockParentBH.getNumber()).thenReturn(661988L);

        // generate output
        boolean actual = new BlockNumberRule<>().validate(mockChildBH, mockParentBH);

        // test output
        assertThat(actual).isFalse();
    }

    /**
     * Checks if the {@link BlockNumberRule#validate} returns {@code false} when
     * the block number is <b>smaller by more than 1</b> than the parent block
     * number.
     */
    @Test
    public void testValidateWithLargerParentNumber() {
        // define return value for method getNumber()
        when(mockChildBH.getNumber()).thenReturn(661985L);
        when(mockParentBH.getNumber()).thenReturn(661987L);

        // generate output
        boolean actual = new BlockNumberRule<>().validate(mockChildBH, mockParentBH);

        // test output
        assertThat(actual).isFalse();
    }
}
