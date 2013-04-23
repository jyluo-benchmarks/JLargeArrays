/* ***** BEGIN LICENSE BLOCK *****
 * 
 * JLargeArrays is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or (at your option) any later version.
 *
 * JLargeArrays is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this Module; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA
 *
 * ***** END LICENSE BLOCK ***** */

package pl.edu.icm.jlargearrays;

import sun.misc.Cleaner;

/**
*
* An array of shorts that can store up to 2^63 elements.
*
* @author Piotr Wendykier (p.wendykier@icm.edu.pl)
*/
public class ShortLargeArray extends LargeArray {

   	private static final long serialVersionUID = 8813991144303908703L;
	private short[] data;

    public ShortLargeArray(long length) {
        this.type = LargeArrayType.SHORT;
        this.sizeof = 2;
        if (length <= 0) {
            throw new IllegalArgumentException(length + " is not a positive long value");
        }
        this.length = length;
        if (length > LARGEST_32BIT_INDEX) {
            System.gc();
            this.ptr = Utilities.UNSAFE.allocateMemory(this.length * this.sizeof);
            zeroMemory();
            Cleaner.create(this, new Deallocator(this.ptr, this.length, this.sizeof));
            MemoryCounter.increaseCoutner(this.length * this.sizeof);
        } else {
            data = new short[(int) length];
        }
    }
    
    public ShortLargeArray(short[] data) {
        this.type = LargeArrayType.SHORT;
        this.sizeof = 2;
        this.length = data.length;
        this.data = data;
    }

    @Override
    public boolean getBoolean(long i) {
        if(isLarge()) {
            return (Utilities.UNSAFE.getShort(ptr + sizeof * i)) == 0 ? false : true;
        }
        else {
            return data[(int)i] == 0 ? false : true;
        }
    }

    @Override
    public byte getByte(long i) {
        if (isLarge()) {
            return (byte) (Utilities.UNSAFE.getShort(ptr + sizeof * i));
        } else {
            return (byte) data[(int) i];
        }
    }

    @Override
    public short getShort(long i) {
        if (isLarge()) {
            return Utilities.UNSAFE.getShort(ptr + sizeof * i);
        } else {
            return data[(int) i];
        }
    }

    @Override
    public int getInt(long i) {
        if (isLarge()) {
            return (int) (Utilities.UNSAFE.getShort(ptr + sizeof * i));
        } else {
            return (int) data[(int) i];
        }
    }

    @Override
    public long getLong(long i) {
        if (isLarge()) {
            return (long) (Utilities.UNSAFE.getShort(ptr + sizeof * i));
        } else {
            return (long) data[(int) i];
        }
    }

    @Override
    public float getFloat(long i) {
        if (isLarge()) {
            return (float) (Utilities.UNSAFE.getShort(ptr + sizeof * i));
        } else {
            return (float) data[(int) i];
        }
    }

    @Override
    public double getDouble(long i) {
        if (isLarge()) {
            return (double) (Utilities.UNSAFE.getShort(ptr + sizeof * i));
        } else {
            return (double) data[(int) i];
        }
    }
    
    @Override
    public boolean[] getBoolData() {
        if(isLarge()) {
            return null;
        }
        else {
            boolean[] res = new boolean[(int)length];
            for (int i = 0; i < length; i++) {
                res[i] = data[i] == 0 ? false : true;
                
            }
            return res;
        }
    }

    @Override
    public byte[] getBData() {
        if (isLarge()) {
            return null;
        } else {
            byte[] res = new byte[(int) length];
            for (int i = 0; i < length; i++) {
                res[i] = (byte) data[i];

            }
            return res;
        }
    }

    @Override
    public short[] getSData() {
        if (isLarge()) {
            return null;
        } else {
            return data;
        }
    }

    @Override
    public int[] getIData() {
        if (isLarge()) {
            return null;
        } else {
            int[] res = new int[(int) length];
            for (int i = 0; i < length; i++) {
                res[i] = (int) data[i];

            }
            return res;
        }
    }

    @Override
    public long[] getLData() {
        if (isLarge()) {
            return null;
        } else {
            long[] res = new long[(int) length];
            for (int i = 0; i < length; i++) {
                res[i] = (long) data[i];

            }
            return res;
        }
    }

    @Override
    public float[] getFData() {
        if (isLarge()) {
            return null;
        } else {
            float[] res = new float[(int) length];
            for (int i = 0; i < length; i++) {
                res[i] = (float) data[i];

            }
            return res;
        }
    }

    @Override
    public double[] getDData() {
        if (isLarge()) {
            return null;
        } else {
            double[] res = new double[(int) length];
            for (int i = 0; i < length; i++) {
                res[i] = (double) data[i];

            }
            return res;
        }
    }
    
    @Override
    public void setBoolean(long i, boolean value) {
        if(isLarge()) { 
            Utilities.UNSAFE.putShort(ptr + sizeof * i, value == true ? (short)1 : (short)0);
        }
        else {
            data[(int)i] = value == true ? (short)1 : (short)0;
        }
    }

    @Override
    public void setByte(long i, byte value) {
        if (isLarge()) {
            Utilities.UNSAFE.putShort(ptr + sizeof * i, (short) value);
        } else {
            data[(int) i] = (short) value;
        }
    }

    @Override
    public void setShort(long i, short value) {
        if (isLarge()) {
            Utilities.UNSAFE.putShort(ptr + sizeof * i, value);
        } else {
            data[(int) i] = value;
        }
    }

    @Override
    public void setInt(long i, int value) {
        if (isLarge()) {
            Utilities.UNSAFE.putShort(ptr + sizeof * i, (short) value);
        } else {
            data[(int) i] = (short) value;
        }
    }

    @Override
    public void setLong(long i, long value) {
        if (isLarge()) {
            Utilities.UNSAFE.putShort(ptr + sizeof * i, (short) value);
        } else {
            data[(int) i] = (short) value;
        }
    }

    @Override
    public void setFloat(long i, float value) {
        if (isLarge()) {
            Utilities.UNSAFE.putShort(ptr + sizeof * i, (short) value);
        } else {
            data[(int) i] = (short) value;
        }
    }

    @Override
    public void setDouble(long i, double value) {
        if (isLarge()) {
            Utilities.UNSAFE.putShort(ptr + sizeof * i, (short) value);
        } else {
            data[(int) i] = (short) value;
        }
    }
}
