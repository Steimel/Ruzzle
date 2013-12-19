/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ruzzlesolver;

import java.util.ArrayList;

/**
 *
 * @author Student
 */
public class StringHashTable
{
    private final double LOAD_FACTOR = 0.75;
    private String[] table;
    private int count;
    private final int INITIAL_SIZE = 1009;

    public StringHashTable()
    {
        count = 0;
        table = new String[INITIAL_SIZE];
    }

    public void insert(String s)
    {
        int pos = Math.abs(s.hashCode() % table.length);
        while(table[pos] != null)
        {
            if(table[pos].equalsIgnoreCase(s)) return;
            pos++;
            pos %= table.length;
            pos = Math.abs(pos);
        }
        table[pos] = s;
        count++;
        if((count * 1.0)/(table.length * 1.0) > LOAD_FACTOR)
        {
            resize();
        }
    }

    public boolean exists(String s)
    {
        int pos = s.hashCode() % table.length;
        pos = Math.abs(pos);
        while(table[pos] != null)
        {
            if(table[pos].equalsIgnoreCase(s)) return true;
            pos++;
            pos %= table.length;
            pos = Math.abs(pos);
        }
        return false;
    }


    private void resize()
    {
        int newSize = nextPrime(table.length*2+1);

        ArrayList<String> oldData = new ArrayList<String>();
        for(int x = 0; x < table.length; x++)
        {
            if(table[x] != null) oldData.add(table[x]);
        }
        table = new String[newSize];
        count = 0;
        for(int x = 0; x < oldData.size(); x++)
        {
            insert(oldData.get(x));
        }
    }

    private int nextPrime(int in)
    {
        if(in % 2 == 0) in += 1;
        while(!checkPrime(in)) in += 2;
        return in;
    }

    private boolean checkPrime(int in)
    {
        for(int x = 2; x < Math.sqrt(in); x++)
        {
            if(in % x == 0) return false;
        }
        return true;
    }
}