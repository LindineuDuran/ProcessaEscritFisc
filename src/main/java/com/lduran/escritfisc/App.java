package com.lduran.escritfisc;

import java.awt.EventQueue;

import com.lduran.escritfisc.gui.FrmEscritFisc;

/**
 * Hello world!
 *
 */
public class App
{
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run()
			{
				FrmEscritFisc form = new FrmEscritFisc();
			}
		});
	}
}
