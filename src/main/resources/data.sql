CREATE OR REPLACE FUNCTION restructure_emission() RETURNS VOID AS $$
    BEGIN
      UPDATE emission SET sector = 'Energy' where sector like '1.%';
      UPDATE emission SET sector = 'Industrial Processes' where sector like '2.%';
      UPDATE emission SET sector = 'Solvent and Other Product Use' where sector like '3.%';
      UPDATE emission SET sector = 'Agriculture' WHERE sector like '4.%';
      UPDATE emission SET sector = 'LULUCF' WHERE sector like '5.%';
      UPDATE emission SET sector = 'Waste' WHERE sector like '6.%';
      UPDATE emission SET sector = 'Other' WHERE sector like '7.%';
      DELETE FROM emission WHERE sector NOT IN ('Energy', 'Industrial Processes', 'Solvent and Other Product Use',
      'Agriculture', 'LULUCF', 'Waste', 'Other'
      );
    END;
    $$ LANGUAGE plpgsql;